package fr.tse.fise3.pri.p002.server.service;


import fr.tse.fise3.pri.p002.server.model.Author;
import fr.tse.fise3.pri.p002.server.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {


    @Autowired
    private AuthorRepository authorRepository;


    public Author findOrCreateByName(String name) {
        Optional<Author> optionalAuthor = authorRepository.findByAuthorName(name);

        if (optionalAuthor.isPresent()) {
            return optionalAuthor.get();
        }

        Author author = new Author();
        author.setAuthorName(name);
        return authorRepository.saveAndFlush(author);
    }


}
