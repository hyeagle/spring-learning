package com.example.jpa.advance.controller;

import com.example.jpa.advance.dao.UserOnlyNameEmailEntityRepository;
import com.example.jpa.advance.dao.UserRepository;
import com.example.jpa.advance.model.User;
import com.example.jpa.advance.model.UserCount;
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
        List<UserInterface> njs = userRepository.findByAddress("nj");
        for (UserInterface nj : njs) {
            System.out.println("name: " + nj.getName());
            System.out.println("email: " + nj.getEmail());
            System.out.println("full: " + nj.getFull());
        }
        return njs.get(0);
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

    @GetMapping("/user/group/count")
    @ResponseBody
    public List<UserCount> testGroupCount() {
        userRepository.save(User.builder().name("ross1").email("ross1@email.com").sex("man").address("nj").build());
        userRepository.save(User.builder().name("ross1").email("ross1@email.com").sex("man").address("nj").build());
        userRepository.save(User.builder().name("ross2").email("ross1@email.com").sex("man").address("nj").build());
        userRepository.save(User.builder().name("ross2").email("ross1@email.com").sex("man").address("nj").build());
        userRepository.save(User.builder().name("ross2").email("ross1@email.com").sex("man").address("nj").build());
        userRepository.save(User.builder().name("ross3").email("ross1@email.com").sex("man").address("nj").build());
        List<UserCount> userCounts = userRepository.groupCount();
        List<UserCount> userMax = userRepository.groupMax();
        userCounts.addAll(userMax);
        for (UserCount uc : userCounts) {
            System.out.println("name: " + uc.getName() + ", count: " + uc.getCountNum() + ", max: " + uc.getMaxId());
        }
        return userCounts;
    }
}
