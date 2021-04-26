package com.example.jpa.advance.controller;

import com.example.jpa.advance.dao.UserOnlyNameEmailEntityRepository;
import com.example.jpa.advance.dao.UserRepository;
import com.example.jpa.advance.model.User;
import com.example.jpa.advance.model.UserDto;
import com.example.jpa.advance.model.UserInterface;
import com.example.jpa.advance.model.UserOnlyNameEmailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Resource
    private UserRepository userRepository;

    @Resource
    private UserOnlyNameEmailEntityRepository userOnlyNameEmailEntityRepository;

    @GetMapping("/user/projection")
    @ResponseBody
    public UserOnlyNameEmailEntity testProjections() {
        userRepository.save(User.builder().id(1L).name("ross").email("ross@email.com").sex("man").address("nj").build());
        return userOnlyNameEmailEntityRepository.findAll().get(0);
    }

    @GetMapping("/user/dto")
    @ResponseBody
    public UserDto testDto() {
        userRepository.save(User.builder().name("ross").email("ross@email.com").sex("man").address("nj").build());
        return userRepository.findByName("ross").get(0);
    }

    @GetMapping("/user/interface")
    @ResponseBody
    public UserInterface testInterface() {
        userRepository.save(User.builder().name("ross").email("ross@email.com").sex("man").address("nj").build());
        return userRepository.findByAddress("nj").get(0);
    }

    @GetMapping("/user/query")
    @ResponseBody
    public List<User> testQuery(@RequestParam String name) {
        return userRepository.findByQuery(name);
    }

    @GetMapping("/user/query/like")
    @ResponseBody
    public List<User> testLike(@RequestParam String name) {
        return userRepository.findByLike(name);
    }

    @GetMapping("/user/query/native")
    @ResponseBody
    public List<User> testNative(@RequestParam String name, @RequestParam String field) {
        return userRepository.findByNative(name, field);
    }

    @GetMapping("/user/query/page")
    @ResponseBody
    public List<User> testQueryPage(@RequestParam String name) {
        Page<User> byPage = userRepository.findByName(name, PageRequest.of(1, 2));
        return byPage.getContent();
    }
}
