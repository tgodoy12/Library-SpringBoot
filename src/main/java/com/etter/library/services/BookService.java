package com.etter.library.services;

import com.etter.library.exceptions.LibraryExceptions;
import com.etter.library.persistence.entities.Autor;
import com.etter.library.persistence.entities.Book;
import com.etter.library.persistence.entities.Publisher;
import com.etter.library.persistence.repository.AutorRepository;
import com.etter.library.persistence.repository.BookRepository;
import com.etter.library.persistence.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    public void createBook(Long isbn, String title, Integer copies, String autorId, String publisherId)
            throws LibraryExceptions {

        //llamar al método findById para verificar si ya existe
        Optional<Book> result = bookRepository.findById(isbn);

        if (result.isPresent()) {
            throw new LibraryExceptions("El libro con ISBN: " + isbn + " ya existe");
        }

        //verificar si autor y publisher ya existen, de lo contrario, se crea uno nuevo.
        Optional<Autor> autorResult = autorRepository.findById(autorId);
        Optional<Publisher> publisherResult = publisherRepository.findById(publisherId);

        Autor newAutor = new Autor();
        Publisher newPublisher = new Publisher();

        if (autorResult.isPresent()) {
            newAutor = autorResult.get();
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
        newBook.setAutor(newAutor);

        //persistir a la bd con el metodo save de bookRepository
        bookRepository.save(newBook);
    }
    /**
     * Tareas:
     * 1-Buscar reducir la cantidad de lineas de codigo. Puede ser con un constructor con parametros
     * para la entidad book. Probar de las dos maneras
     *
     * 2-Crear el metodo validate() que valida que los argumentos que se mandan no sean nullos.
     * 3-Continuar con el resto de los métodos
     */
}
