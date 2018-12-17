package fr.tse.fise3.pri.p002.commons;

import java.util.concurrent.BlockingQueue;

public class PostConsumer implements Runnable {

    private final BlockingQueue<Post> postBlockingQueue;

    public PostConsumer(BlockingQueue<Post> postBlockingQueue) {
        this.postBlockingQueue = postBlockingQueue;
    }

    public void run() {
        try {
            Post currentPost = this.postBlockingQueue.take();
            while (currentPost != PostProducer.POST_POISON_PILL) {
                System.out.println(currentPost);
                currentPost = this.postBlockingQueue.take();
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
