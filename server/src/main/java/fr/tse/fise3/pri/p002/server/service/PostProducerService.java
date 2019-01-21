package fr.tse.fise3.pri.p002.server.service;

import fr.tse.fise3.pri.p002.server.model.Author;
import fr.tse.fise3.pri.p002.server.model.Keyword;
import fr.tse.fise3.pri.p002.server.model.Post;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
public class PostProducerService implements Runnable {
    static final BlockingQueue<Post> POST_BLOCKING_QUEUE = new ArrayBlockingQueue<>(100);
    static Post POST_POISON_PILL = new Post();

    @Autowired
    private AuthorService authorService;

    @Autowired
    private KeywordService keywordService;

    public PostProducerService() {
    }

    @Transactional
    public void run() {

        try {
            Document document = Jsoup.connect("https://eprint.iacr.org/complete/").get();

            Elements elements = document.select("body > dl > dt > a:nth-child(1)");
            int i = 0;
            for (Element element : elements) {
                Document pageDocument = Jsoup.connect("https://eprint.iacr.org" + element.attr("href")).get();

                Elements pageBody = pageDocument.select("body");

                Post post = new Post();
                post.setTitle(pageDocument.select("body > b").first().text());

                for (int j = 0; ; j++) {
                    if (pageBody.get(0).child(j).text().contains("Category / Keywords")) {
                        String keywords = pageBody.get(0).child(j).nextSibling().toString();
                        keywords = StringUtils.substringBefore(keywords, "<");
                        keywords = StringUtils.substringBefore(keywords, "received");

                        List<Keyword> keywordList = new ArrayList<>();
                        for (String keywordString : StringUtils.split(keywords, ",;")) {
                            Keyword savedKeyword = keywordService.findOrCreateByName(keywordString.toLowerCase().trim());
                            keywordList.add(savedKeyword);
                        }

                        post.setKeywords(keywordList);
                    }
                    if (pageBody.get(0).child(j).text().contains("Date:")) {
                        String date = pageBody.get(0).child(j).nextSibling().toString();
                        date = StringUtils.substringAfter(date, "received");
//                      Todo  post.setDate(date.trim());
                        post.setDate(new Date());

                    }
                    if (pageBody.get(0).child(j).text().contains("Contact author:")) {
                        String authorString = pageBody.get(0).child(j).nextSibling().toString();
                        Author author = authorService.findOrCreateByName(authorString.toLowerCase().trim());
                        post.setAuthorId(author.getAuthorId());
                        break;
                    }
                }

                POST_BLOCKING_QUEUE.put(post);

                if (++i == 10) {
                    POST_BLOCKING_QUEUE.put(POST_POISON_PILL);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
