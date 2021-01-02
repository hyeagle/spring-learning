package com.example.jpa.book;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
public class RedBook extends Book {
    private String redMark;
}
