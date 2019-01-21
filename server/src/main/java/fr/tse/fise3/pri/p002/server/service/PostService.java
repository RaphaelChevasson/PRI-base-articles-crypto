package fr.tse.fise3.pri.p002.server.service;

import fr.tse.fise3.pri.p002.server.model.Keyword;
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
    public Post savePost(Post post) {
        System.out.println("Saving post");
        System.out.println(post.getTitle());
        System.out.println(post.getKeywords());


        System.out.println(post.getAuthorId());
        System.out.println(post.getPostId());

        try {
            return postRepository.save(post);
        } catch (Exception e) {
            System.out.println(post.getPostId());
            for (Keyword keyword : post.getKeywords()) {
                System.out.println("Keyword ---> " + keyword.getKeywordName() + " id --> " + keyword.getKeywordId());
            }
            throw new RuntimeException(e);
        }
    }


}
