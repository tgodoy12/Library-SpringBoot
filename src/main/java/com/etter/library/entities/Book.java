package com.etter.library.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Book {

    @Id
    private Long isbn;
    @Column
    private String titulo;
    private Integer ejemplares;

    @Temporal(TemporalType.DATE)
    private Date alta;
}
