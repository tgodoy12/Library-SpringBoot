package com.etter.library.services;

import com.etter.library.exceptions.LibraryExceptions;
import com.etter.library.persistence.entities.Author;
import com.etter.library.persistence.entities.Book;
import com.etter.library.persistence.entities.Publisher;
import com.etter.library.persistence.repository.AuthorRepository;
import com.etter.library.persistence.repository.BookRepository;
import com.etter.library.persistence.repository.PublisherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    @Transactional
    public void createBook(Long isbn, String title, Integer copies, String authorId, String publisherId)
            throws LibraryExceptions {

        //validar valores pasados por parámentros
        validate(isbn, title, copies, authorId, publisherId);

        //llamar al método findById para verificar si ya existe
        Optional<Book> result = bookRepository.findById(isbn);

        if (result.isPresent()) {
            throw new LibraryExceptions("El libro con ISBN: " + isbn + " ya existe");
        }

        //verificar si autor y publisher ya existen, de lo contrario, se crea uno nuevo.
        Optional<Author> authorResult = authorRepository.findById(authorId);
        Optional<Publisher> publisherResult = publisherRepository.findById(publisherId);

        Author newAuthor = new Author();
        Publisher newPublisher = new Publisher();

        if (authorResult.isPresent()) {
            newAuthor = authorResult.get();
        }

        if (publisherResult.isPresent()) {
            newPublisher = publisherResult.get();
        }

        //crear nuevo libro y setear los parametros recibidos
        Book newBook = new Book();

        newBook.setIsbn(isbn);
        newBook.setTitle(title);
        newBook.setCopies(copies);
        newBook.setRegistered(new Date());
        newBook.setPublisher(newPublisher);
        newBook.setAuthor(newAuthor);

        //persistir a la bd con el metodo save de bookRepository
        bookRepository.save(newBook);
    }

    @Transactional
    public void modifyBook(Long isbn, String title, Integer copies, String authorId, String publisherId) throws
            LibraryExceptions {

        validate(isbn, title, copies, authorId, publisherId);

        Optional<Book> bookResult = bookRepository.findById(isbn);
        Optional<Author> authorResult = authorRepository.findById(authorId);
        Optional<Publisher> publisherResult = publisherRepository.findById(publisherId);

        Author author = new Author();
        Publisher publisher = new Publisher();

        if (authorResult.isPresent()) {
            author = authorResult.get();
        }

        if (publisherResult.isPresent()) {
            publisher = publisherResult.get();
        }

        if (bookResult.isPresent()) {
            Book book = bookResult.get();

            book.setTitle(title);
            book.setCopies(copies);
            book.setAuthor(author);
            book.setPublisher(publisher);

            bookRepository.save(book);
        }

    }

     /**
     * verificar que funcione, sino pruebo de la forma
     * larga definiendo una List<Book>
     */
    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public void validate(Long isbn, String title, Integer copies, String authorId, String publisherId) throws
            LibraryExceptions {

        if(isbn == null) {
            throw new LibraryExceptions("ISBN can't be null");
        }
        if(title.isEmpty()) {
            throw new LibraryExceptions("Title can't be empty");
        }
        if(copies == null) {
            throw new LibraryExceptions("Copies can't be null");
        }
        if(authorId.isEmpty()) {
            throw new LibraryExceptions("Author can't be empty");
        }
        if(publisherId.isEmpty()) {
            throw new LibraryExceptions("Publisher can't be empty");
        }
    }
    /*
      Tareas:
      1-Buscar reducir la cantidad de lineas de codigo. Puede ser con un constructor con parametros
      para la entidad book. Probar de las dos maneras

      3-Continuar con el resto de los métodos
     */
}
