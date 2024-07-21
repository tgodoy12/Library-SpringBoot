package com.etter.library.persistence.repository;

import com.etter.library.persistence.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    public Book findByTitle(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE b.author.name = :name")
    public ArrayList<Book> findBooksByAuthor(@Param("name") String name);

}
