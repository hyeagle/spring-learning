package com.example.jpa.advance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOnlyNameEmailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;
}
