package com.example.jpa.jse;

import com.example.jpa.jse.model.SexEnum;
import com.example.jpa.jse.model.User;
import com.example.jpa.jse.model.UserAddress;
import com.example.jpa.jse.repository.UserAddressRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.el.parser.AstFalse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;

@DataJpaTest // 使用一个空数据库
@TestInstance(TestInstance.Lifecycle.PER_CLASS)  // 隔离测试方法，每个测试类创建一个新的测试实例
public class UserAddressRepositoryTest {
    @Autowired
    private UserAddressRepository userAddressRepository;

    private Date now = new Date();

    // 添加数据
    @BeforeAll
    @Rollback
    @Transactional
    void init() {
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

    @Test
    @Rollback(false)
    public void testQBEFromUserAddress() throws JsonProcessingException {
        User request = User.builder().name("ross").age(20).email("ross").build();
        UserAddress address = UserAddress.builder().address("n").user(request).build();
        ObjectMapper mapper = new ObjectMapper();

        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(address)); // 可以打印出来看参数

        // 创建匹配其
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("user.email", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("address", ExampleMatcher.GenericPropertyMatchers.startsWith());
        Page<UserAddress> page = userAddressRepository.findAll(Example.of(address, exampleMatcher), PageRequest.of(0, 2));
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(page));
    }
}
