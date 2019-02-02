package fr.tse.fise3.pri.p002.server.service;

import fr.tse.fise3.pri.p002.server.model.Author;
import fr.tse.fise3.pri.p002.server.model.DataSource;
import fr.tse.fise3.pri.p002.server.model.Keyword;
import fr.tse.fise3.pri.p002.server.model.Post;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
public class EPrintPostProducerService implements Runnable {
    static final BlockingQueue<Post> POST_BLOCKING_QUEUE = new ArrayBlockingQueue<>(100);
    static Post POST_POISON_PILL = new Post();

    @Autowired
    private AuthorService authorService;

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private PostService postService;

    @Autowired
    private DataSourceService dataSourceService;

    public EPrintPostProducerService() {
    }

    public void run() {

        try {
            Document document = Jsoup.connect("https://eprint.iacr.org/complete/").get();

            Elements elements = document.select("body > dl > dt > a:nth-child(1)");

            DataSource ePrintDataSource = dataSourceService.getEPrintDataSource();
            ePrintDataSource.setTotal(elements.size());
            dataSourceService.saveDataSource(ePrintDataSource);

            for (Element element : elements) {
                String postUrl = "https://eprint.iacr.org" + element.attr("href");
                Document pageDocument = Jsoup.connect(postUrl).get();


                Elements pageBody = pageDocument.select("body");

                String title = pageDocument.select("body > b").first().text().toLowerCase().trim();

                if (postService.postExistsByUrl(postUrl)) {
                    System.out.println("POST_ALREADY_EXISTS_BY_URL");
                    continue;
                }

                Post post = new Post();
                post.setTitle(title);
                post.setUrl(postUrl);

                for (int j = 0; ; j++) {
                    if (pageBody.get(0).child(j).text().contains("Category / Keywords")) {
                        String keywords = pageBody.get(0).child(j).nextSibling().toString();
                        keywords = StringUtils.substringBefore(keywords, "<");
                        keywords = StringUtils.substringBefore(keywords, "received");

                        Map<BigInteger, Keyword> kaywordMap = new HashMap<>();
                        for (String keywordString : StringUtils.split(keywords, ",;")) {
                            Keyword savedKeyword = keywordService.findOrCreateByName(keywordString.toLowerCase().trim());
                            kaywordMap.put(savedKeyword.getKeywordId(), savedKeyword);
                        }
                        post.setKeywords(new ArrayList<>(kaywordMap.values()));
                    }
                    if (pageBody.get(0).child(j).text().contains("Date:")) {
                        String date = pageBody.get(0).child(j).nextSibling().toString();
                        date = StringUtils.substringAfter(date, "received");
//                      Todo  post.setDate(date.trim());
                        post.setDate(new Date());

                    }
                    if (pageBody.get(0).child(j).text().contains("Contact author:")) {
                        String authorString = pageBody.get(0).child(j).nextSibling().toString();
                        // todo split author string
                        Author author = authorService.findOrCreateByName(authorString.toLowerCase().trim());
                        post.setAuthors(Collections.singletonList(author));
                        break;
                    }
                }

                post.setDataSource(dataSourceService.getEPrintDataSource());
                POST_BLOCKING_QUEUE.put(post);
            }
            POST_BLOCKING_QUEUE.put(POST_POISON_PILL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
