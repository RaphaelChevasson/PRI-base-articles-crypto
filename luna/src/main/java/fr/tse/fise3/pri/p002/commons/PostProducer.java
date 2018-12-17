package fr.tse.fise3.pri.p002.commons;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.concurrent.BlockingQueue;

public class PostProducer implements Runnable {
    public static Post POST_POISON_PILL = new Post();
    private final BlockingQueue<Post> postBlockingQueue;

    public PostProducer(BlockingQueue<Post> postBlockingQueue) {
        this.postBlockingQueue = postBlockingQueue;
    }

    public void run() {

        try {
            Document document = Jsoup.connect("https://eprint.iacr.org/eprint-bin/search.pl?last=10000&title=1").get();

            Elements elements = document.select("body > dl > dt > a:nth-child(1)");
            int i = 0;
            for (Element element : elements) {
                Document pageDocument = Jsoup.connect("https://eprint.iacr.org" + element.attr("href")).get();

                Elements pageBody = pageDocument.select("body");

                Post post = new Post();
                post.setTitle(pageDocument.select("body > b").first().text());

                for (int j = 0; ; j++) {
                    if (pageBody.get(0).child(j).text().contains("Abstract:")) {
                        post.setTopicAbstract(pageBody.get(0).child(j).nextSibling().toString());
                    }
                    if (pageBody.get(0).child(j).text().contains("Category / Keywords")) {
                        String keywords = pageBody.get(0).child(j).nextSibling().toString();
                        keywords = StringUtils.substringBefore(keywords, "<");
                        keywords = StringUtils.substringBefore(keywords, "received");
                        post.setKeywords(keywords);
                    }
                    if (pageBody.get(0).child(j).text().contains("Date:")) {
                        String date = pageBody.get(0).child(j).nextSibling().toString();
                        date = StringUtils.substringAfter(date, "received");
                        post.setDate(date.trim());
                    }
                    if (pageBody.get(0).child(j).text().contains("Contact author:")) {
                        String author = pageBody.get(0).child(j).nextSibling().toString();
                        post.setAuthor(author);
                        break;
                    }
                }

                this.postBlockingQueue.put(post);

                if (++i == 10) {
                    this.postBlockingQueue.put(POST_POISON_PILL);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
