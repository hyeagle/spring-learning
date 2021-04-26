package com.example.jpa.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_user")  // 指定表名
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false)
    private Long id;

    @Column(length = 10, nullable = false)  // column 可以在属性上，也可以在 get/set 方法上，但只有一个能生效，可以通过 Access 干预
    private String name;

    @Column(length = 18, unique = true)
    private Long identityCard;

    @Enumerated(EnumType.STRING)   // type 映射成 0/1，string 映射成字符串
    @Column(name = "user_gender")
    private Gender gender;

    @Column(updatable = false) // 不更新该字段
    @Temporal(TemporalType.TIMESTAMP)  // 日期+时间
    private Date createTime;

    @OneToMany(mappedBy = "user")
    @Transient // 表示不映射，和 Basic 相反。Basic 不需要指定，默认
    private List<UserAddress> address;

    @OneToOne(mappedBy = "user")  // 双向关联
    @Transient  // 不加这个注解的话，带 userInfo 保存会报错
    private UserInfo userInfo;
}

