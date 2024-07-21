package com.etter.library.services;

import com.etter.library.exceptions.LibraryExceptions;
import com.etter.library.persistence.entities.Author;
import com.etter.library.persistence.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Transactional
    public void createAuthor(String name) throws LibraryExceptions {

        //validar el valor pasado por parámetro
        validate(name);

        Author newAuthor = new Author();
        newAuthor.setName(name);

        authorRepository.save(newAuthor);

    }

    public List<Author> getAllAuthors() {

        List<Author> authorList = new ArrayList<>();

        authorList = authorRepository.findAll();

        return authorList;
    }

    @Transactional
    public void modifyAuthor(String id, String name) throws LibraryExceptions {

        validate(name);

        Optional<Author> result = authorRepository.findById(id);

        if (result.isPresent()) {
            Author author = result.get();
            author.setName(name);
            authorRepository.save(author);
        }

    }

    public void validate(String name) throws LibraryExceptions {
        if (name.isEmpty()) {
           throw new LibraryExceptions("Author's name can't be empty");
        }
    }
}
