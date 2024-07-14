package com.etter.library.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    private Long isbn;
    private String title;
    private Integer copies;

    @Temporal(TemporalType.DATE)
    private Date registered;

    @ManyToOne
    private Autor autor;

    @ManyToOne
    private Publisher publisher;
}
