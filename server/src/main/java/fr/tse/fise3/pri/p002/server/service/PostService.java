package fr.tse.fise3.pri.p002.server.service;

import fr.tse.fise3.pri.p002.server.model.Post;
import fr.tse.fise3.pri.p002.server.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional(readOnly = false)
    Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    boolean postExistsByUrl(String url) {
        return postRepository.existsByUrl(url);
    }
}
