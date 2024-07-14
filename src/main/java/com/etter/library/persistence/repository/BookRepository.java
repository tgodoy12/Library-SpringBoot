package com.etter.library.persistence.repository;

import com.etter.library.persistence.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT l FROM Libro l WHERE l.title = :title")
    public Book findByTitle(@Param("title") String title);

    @Query("SELECT l FROM Libro l WHERE l.autor.name = :name")
    public ArrayList<Book> findBooksByAutor(@Param("name") String name);

}
