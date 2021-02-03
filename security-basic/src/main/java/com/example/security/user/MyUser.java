package com.example.security.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyUser implements Serializable {   // 创建一个模拟的用户，实际应该从 db 中读取
    private static final long serialVersionUID = 3497935890426858541L;

    private String userName;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;
}
