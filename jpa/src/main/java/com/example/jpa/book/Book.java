package com.example.jpa.book;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "book")
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
}
