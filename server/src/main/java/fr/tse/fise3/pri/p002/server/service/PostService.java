package fr.tse.fise3.pri.p002.server.service;

import fr.tse.fise3.pri.p002.server.model.Post;
import fr.tse.fise3.pri.p002.server.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional(readOnly = false)
    Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> findPostByTitleLike(String title) {
        return postRepository.findByTitleContaining(title);
    }

    @Transactional(readOnly = true)
    boolean postExistsByUrl(String url) {
        return postRepository.existsByUrl(url);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Post> findByKeywords_keywordNameContaining(String keyword) {
        return postRepository.findByKeywords_keywordNameContaining(keyword);
    }


    @Transactional(readOnly = true)
    public List<Post> findByAuthors_authorNameContaining(String name) {
        return postRepository.findByAuthors_authorNameContaining(name);
    }
}
