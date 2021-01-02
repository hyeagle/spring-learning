package com.example.jpa.user;

import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserInfoRepository userInfoRepository;

    @PostMapping(path = "/user", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User addNewUser(@RequestBody User user) {
        user = userRepository.saveAndFlush(user);
        return user;
    }

    @GetMapping(path = "/user")
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/page")
    @ResponseBody
    public Page<User> getAllUserByPage() {
        return userRepository.findAll(PageRequest.of(1, 20, Sort.by(new Sort.Order(Sort.Direction.ASC, "name"))));
    }

    @GetMapping(path = "/sort")
    @ResponseBody
    public List<User> getAllUsersWithSort() {
        return userRepository.findAll(Sort.by(new Sort.Order(Sort.Direction.ASC, "name")));
    }

    @GetMapping(path = "/name")
    public UserOnlyNameAndEmail getName(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }

    @GetMapping(path = "/projections-interface")
    public List<UserOnlyName> getOnlyName() {
        return userRepository.findOnlyName();
    }

    @PostMapping(path = "/dynamic")
    public List<UserOnlyName> getDynamic(@RequestBody User user) {
        return userRepository.findDynamic(user);
    }

    @PostMapping(path = "/user-info")
    public UserInfo addUserInfo(@RequestBody UserInfo userInfo) {
        return userInfoRepository.saveAndFlush(userInfo);
    }

    @GetMapping(path = "/user-info")
    public List<UserInfo> getUserInfo() {
        return userInfoRepository.findAll();
    }

    @GetMapping(path = "/example")
    public List<UserInfo> getExample() {
        //User user = User.builder().name("ross").userInfo(UserInfo.builder().ages(15).build()).build();
        UserInfo userInfo = UserInfo.builder().ages(16).user(User.builder().name("ross").build()).build();
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("user.name", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("ages", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<UserInfo> example = Example.of(userInfo, matcher);
        List<UserInfo> all = userInfoRepository.findAll(example);
        return all;
    }

    @GetMapping(path = "/version")
    @Transactional
    @Retryable
    public User getVersion(@RequestParam String name, @RequestParam long time) {
        User one = userRepository.getOne(1L);
        //User one = userRepository.findById(1L).get();
        one.setName(name);
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRepository.save(one);
    }

}

