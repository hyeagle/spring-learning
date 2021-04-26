package com.example.jpa.entity.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer age;

    private String telephone;

    // 维护外键关系
    // 可以设置级联关系，懒加载
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private User user;
}
