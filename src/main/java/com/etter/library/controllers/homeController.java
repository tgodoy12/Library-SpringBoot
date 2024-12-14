package com.etter.library.controllers;

import com.etter.library.persistence.entities.Book;
import com.etter.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class homeController {

    @Autowired
    BookService bookService = new BookService();

    @GetMapping("/")
    public List<Book> getAllBooks() {
        return bookService.listAllBooks();
    }
}
