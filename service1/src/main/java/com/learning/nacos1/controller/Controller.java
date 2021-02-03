package com.learning.nacos1.controller;

import com.learning.nacos1.integration.Nacos2Integration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class Controller {

    @Resource
    Nacos2Integration nacos2Integration;

    @GetMapping("/nacos1/api/{id}")
    public String getId(@PathVariable int id) {
        return "nacos1 string is " + id;
    }

    @GetMapping("/nacos1/nacos2/{id}")
    public String getNacos2(@PathVariable int id) {
        log.info("service1 get service2, id: {}", id);
        return nacos2Integration.getNacos2(id);
    }
}
