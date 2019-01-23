package fr.tse.fise3.pri.p002.server.repository;

import fr.tse.fise3.pri.p002.server.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, BigInteger> {

    boolean existsByUrl(String url);

    List<Post> findByTitleContaining(String title);


    List<Post> findByAuthors_authorNameContaining(String name);
    List<Post> findByKeywords_keywordNameContaining(String title);


}
