import fr.tse.fise3.pri.p002.commons.Post;
import fr.tse.fise3.pri.p002.commons.PostConsumer;
import fr.tse.fise3.pri.p002.commons.PostProducer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        final BlockingQueue<Post> postBlockingQueue = new LinkedBlockingQueue<Post>();

        Thread postProducerThread = new Thread(new PostProducer(postBlockingQueue));
        Thread postConsumerThread = new Thread(new PostConsumer(postBlockingQueue));

        postProducerThread.start();
        postConsumerThread.start();
    }
}
