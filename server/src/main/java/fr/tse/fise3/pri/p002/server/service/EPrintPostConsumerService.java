package fr.tse.fise3.pri.p002.server.service;

import fr.tse.fise3.pri.p002.server.model.DataSource;
import fr.tse.fise3.pri.p002.server.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EPrintPostConsumerService implements Runnable {

    @Autowired
    private PostService postService;

    @Autowired
    private DataSourceService dataSourceService;

    private AtomicInteger atomicInteger = new AtomicInteger(0);


    public void run() {
        Post currentPost;
        try {
            currentPost = EPrintPostProducerService.POST_BLOCKING_QUEUE.take();
            while (currentPost != EPrintPostProducerService.POST_POISON_PILL) {
                savePost(currentPost);
                updateEPrintOffset(atomicInteger.incrementAndGet());
                currentPost = EPrintPostProducerService.POST_BLOCKING_QUEUE.take();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void savePost(Post post) {
        postService.savePost(post);
    }

    private void updateEPrintOffset(int value) {
        DataSource ePrintDataSource = dataSourceService.getEPrintDataSource();
        ePrintDataSource.setCurrentOffset(value);
        dataSourceService.saveDataSource(ePrintDataSource);
    }

}
