package com.example.jpa.entity.controller;

import com.example.jpa.entity.model.User;
import com.example.jpa.entity.model.UserAddress;
import com.example.jpa.entity.model.UserInfo;
import com.example.jpa.entity.repository.UserAddressRepository;
import com.example.jpa.entity.repository.UserInfoRepository;
import com.example.jpa.entity.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Resource
    UserRepository userRepository;

    @Resource
    UserInfoRepository userInfoRepository;

    @Resource
    UserAddressRepository userAddressRepository;

    @GetMapping("/user")
    public List<User> getUser() {
        return userRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"))).getContent();
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        User save = userRepository.save(user);
        UserInfo userInfo = user.getUserInfo();
        if (userInfo != null) {
            userInfo.setUser(save);
            userInfo = userInfoRepository.save(userInfo);
        }
        save.setUserInfo(userInfo);
        return save;
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/address")
    public void addAddress(@RequestBody UserAddress userAddress) {

        userAddressRepository.save(userAddress);
    }
}
