package com.example.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }

    @GetMapping("/index")
    public Object index() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/session/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String sessionInvalid() {
        return "session 已失效，请重新认证";
    }

    @GetMapping("/signout/success")
    public String signout() {
        return "推出成功";
    }

    @GetMapping("/auth/admin")
    @PreAuthorize("hasAuthority('admin')")
    public String authenticationTest() {
        return "您有 admin 权限";
    }
}
