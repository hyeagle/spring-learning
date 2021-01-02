package com.example.jpa.book;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class BlueBook extends Book {
    private String blueMark;
}
