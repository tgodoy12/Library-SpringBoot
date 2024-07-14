package com.etter.library.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;


@Data
@Entity
@Table(name = "publisher")
public class Publisher {

    @Id
    @UuidGenerator
    private String id;
    private String name;
}
