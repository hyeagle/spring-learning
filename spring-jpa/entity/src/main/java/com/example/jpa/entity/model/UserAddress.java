package com.example.jpa.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
}
