package com.example.jpa.base.controller;

import com.example.jpa.base.dao.UserRepository;
import com.example.jpa.base.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Resource
    private UserRepository userRepository;

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/user")
    public List<User> getUser(@RequestParam String name) {
        return userRepository.findByName(name);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam Long id) {
        User user = User.builder().id(id).build();
        userRepository.delete(user);
    }

    @GetMapping("/user/sort")
    public List<User> getUserSort() {
        return userRepository.findAll(Sort.by(new Sort.Order(Sort.Direction.DESC, "id")));
    }

    @GetMapping("/user/page")
    public List<User> getUserPage() {
        // page 参数是从 0 开始计数的
        Page<User> users = userRepository.findAll(PageRequest.of(1, 2, Sort.by(new Sort.Order(Sort.Direction.DESC, "id"))));
        return users.getContent();
    }

    @GetMapping("/user/page2")
    // Nullable 注解，参数可以为空
    public List<User> getUserPage2(@Nullable @RequestParam String name) {
        return userRepository.findByName(name, PageRequest.of(1, 2, Sort.by(new Sort.Order(Sort.Direction.DESC, "id"))));
    }
}
