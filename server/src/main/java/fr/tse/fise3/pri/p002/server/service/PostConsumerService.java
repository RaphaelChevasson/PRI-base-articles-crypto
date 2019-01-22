package fr.tse.fise3.pri.p002.server.service;

import fr.tse.fise3.pri.p002.server.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostConsumerService implements Runnable {

    @Autowired
    private PostService postService;


    public void run() {
        Post currentPost = null;
        try {
            currentPost = PostProducerService.POST_BLOCKING_QUEUE.take();
            while (currentPost != PostProducerService.POST_POISON_PILL) {
                savePost(currentPost);
                currentPost = PostProducerService.POST_BLOCKING_QUEUE.take();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = false)
    private void savePost(Post post) {
        postService.savePost(post);
    }

}
