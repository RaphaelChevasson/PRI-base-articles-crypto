package fr.tse.fise3.pri.p002.server.service;

import fr.tse.fise3.pri.p002.server.model.Post;
import fr.tse.fise3.pri.p002.server.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional(readOnly = false)
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Transactional(readOnly = false)
    public void savePostAndFlush(Post post) {
        postRepository.saveAndFlush(post);
    }

    @Transactional(readOnly = true)
    public Page<Post> findPostByTitleLike(String title, Pageable pageable) {
        return postRepository.findByTitleContaining(title, pageable);
    }

    @Transactional(readOnly = true)
    public boolean postExistsByUrl(String url) {
        return postRepository.existsByUrl(url);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Post> findByKeywords_keywordNameContaining(String keyword, Pageable pageable) {
        return postRepository.findByKeywords_keywordNameContaining(keyword, pageable);
    }


    @Transactional(readOnly = true)
    public Page<Post> findByAuthors_authorNameContaining(String name, Pageable pageable) {
        return postRepository.findByAuthors_authorNameContaining(name, pageable);
    }
}
