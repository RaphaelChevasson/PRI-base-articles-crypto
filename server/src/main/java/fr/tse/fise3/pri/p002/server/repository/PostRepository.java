package fr.tse.fise3.pri.p002.server.repository;

import fr.tse.fise3.pri.p002.server.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PostRepository extends JpaRepository<Post, BigInteger> {

    boolean existsByUrl(String url);

    Page<Post> findByTitleContaining(String title, Pageable pageable);

    Page<Post> findByAuthors_authorNameContaining(String name, Pageable pageable);

    Page<Post> findByKeywords_keywordNameContaining(String title, Pageable pageable);

}
