package com.etter.library.controllers;

import com.etter.library.dto.AuthorDTO;
import com.etter.library.exceptions.LibraryExceptions;
import com.etter.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/author")
public class authorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/new")
    public void registerAuthor(@RequestBody AuthorDTO authorDTO) {
        try {
            authorService.createAuthor(authorDTO.getName());
        } catch (LibraryExceptions e) {
            Logger.getLogger(authorController.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
