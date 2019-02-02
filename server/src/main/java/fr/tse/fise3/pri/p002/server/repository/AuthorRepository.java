package fr.tse.fise3.pri.p002.server.repository;

import fr.tse.fise3.pri.p002.server.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, BigInteger> {

    Optional<Author> findByAuthorName(String name);

}
