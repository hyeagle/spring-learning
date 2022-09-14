package com.learning.nacos1.controller;

import com.learning.nacos1.comditionbean.BeanCondition;
import com.learning.nacos1.comditionbean.BeanCreate;
import com.learning.nacos1.component.LazyDemo;
import com.learning.nacos1.conditional.CmdService;
import com.learning.nacos1.integration.Nacos2Integration;
import com.learning.nacos1.pojo.BeanDemo1;
import com.learning.nacos1.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@EnableConfigurationProperties({User.class})
public class Controller {

    @Resource
    Nacos2Integration nacos2Integration;

//    @Resource
//    LazyDemo lazyDemo;

    @Autowired
    BeanDemo1 beanDemo1;

//    @Autowired
//    BeanCondition beanCondition;

    @Resource
    CmdService cmdService;
//    @Resource
//    User user;

//    @Autowired
//    BeanCreate beanCreate;

    @GetMapping("/nacos1/api/{id}")
    public String getId(@PathVariable int id) {
        return "nacos1 string is " + id;
    }

    @GetMapping("/nacos1/nacos2/{id}")
    public String getNacos2(@PathVariable int id) {
        log.info("service1 get service2, id: {}", id);
        return nacos2Integration.getNacos2(id);
    }

    @GetMapping("lazy")
    public void lazy() {
//        lazyDemo.begin();
        beanDemo1.begin();
//        user.getAge();
        cmdService.print();
//        beanCreate.print();
//        beanCondition.print();
    }
}
