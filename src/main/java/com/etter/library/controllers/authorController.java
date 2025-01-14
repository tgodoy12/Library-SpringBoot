package com.etter.library.controllers;

import com.etter.library.dto.AuthorDTO;
import com.etter.library.exceptions.LibraryExceptions;
import com.etter.library.persistence.entities.Author;
import com.etter.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/author")
public class authorController {

    @Autowired
    private AuthorService authorService;


    @GetMapping("/{id}")
    public Optional<Author> getAuthor(@PathVariable String id) {
            return authorService.getAuthor(id);
    }
    //hacer el manejo de excepciones para el caso de que no haya authores con ese id
    //ver la guia api rest para ver el manejo de excepciones

    @GetMapping("/all")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping("/new")
    public void registerAuthor(@RequestBody AuthorDTO authorDTO) {
        try {
            authorService.createAuthor(authorDTO.getName());
        } catch (LibraryExceptions e) {
            Logger.getLogger(authorController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable String id, @RequestBody AuthorDTO authorDTO) {
        try {
            authorService.updateAuthor(id, authorDTO.getName());
            return ResponseEntity.ok("Author updated successfully");
        } catch (LibraryExceptions e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }


}
