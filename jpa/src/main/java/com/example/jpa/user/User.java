package com.example.jpa.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String sex;

    private String address;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @Transient
    @JsonBackReference
    private UserInfo userInfo;

    @Version
    private Integer version;
}
