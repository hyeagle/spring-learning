package com.example.jpa.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer ages;

    private String telephone;

    //private Long userId;

    @OneToOne(cascade = {CascadeType.PERSIST}, orphanRemoval = true)//, fetch = FetchType.LAZY)
    //@Transient
    //@JsonBackReference
    private User user;

}
