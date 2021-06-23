package com.example.jpa.jse.controller;

import com.example.jpa.jse.model.SexEnum;
import com.example.jpa.jse.model.User;
import com.example.jpa.jse.model.UserAddress;
import com.example.jpa.jse.repository.UserAddressRepository;
import com.example.jpa.jse.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @author: hongyu
 * @date: 2021-04-27
 */
@RestController
@RequestMapping("/api/v1/address")
public class UserAddressController {

    @Resource
    private UserAddressRepository userAddressRepository;

    @Resource
    private UserRepository userRepository;

    @PostMapping
    public void add() {
        Date now = new Date();
        User user = User.builder()
                .name("ross")
                .email("ross@example.com")
                .sex(SexEnum.BOY)
                .age(20)
                .createDate(Instant.now())
                .updateDate(now)
                .build();
        userAddressRepository.saveAll(Lists.newArrayList(UserAddress.builder().user(user).address("nj").build(),
                UserAddress.builder().user(user).address("sh").build()));
    }

    @GetMapping("/example")
    public List<UserAddress> getByExample(@RequestParam String name, @RequestParam Integer age, @RequestParam String email, @RequestParam String addr) {
        User user = User.builder().name(name).age(age).email(email).build();
        UserAddress address = UserAddress.builder().address(addr).user(user).build();

        // 创建匹配
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()  // 所有字段用的是 and 查询
                .withIgnorePaths("user.age")  // 该字段不进行匹配
                .withMatcher("user.email", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("address", ExampleMatcher.GenericPropertyMatchers.startsWith());
        return userAddressRepository.findAll(Example.of(address, exampleMatcher), PageRequest.of(0, 2)).getContent();
    }

    @GetMapping("/jse")
    public List<User> getByJSE(@RequestParam String name, @RequestParam Integer age, @RequestParam String email, @RequestParam String addr) {
        User user = User.builder()
                .name(name)
                .email(email)
                .age(age)
                .address(Lists.newArrayList(UserAddress.builder().address(addr).build()))
                .build();
        Page<User> users = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> ps = new ArrayList<>();
                if (!StringUtils.hasLength(user.getName())) {
                    ps.add(criteriaBuilder.like(root.get("name"), "%" + user.getName() + "%"));
                }
                if (user.getAge() != null) {
                    ps.add(criteriaBuilder.greaterThan(root.get("age"), user.getAge()));
                }
                if (!ObjectUtils.isEmpty(user.getAddress())) {
                    ps.add(criteriaBuilder.in(root.join("address").get("address")).value(user.getAddress().stream().map(a -> a.getAddress()).collect(Collectors.toList())));
                }
                return query.where(ps.toArray(new Predicate[0])).getRestriction();
            }
        }, PageRequest.of(0, 2));
        return users.getContent();
    }
}
