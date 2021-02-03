package com.learning.nacos2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {
    @GetMapping("/nacos2/api/{id}")
    public String getId(@PathVariable int id) {
        log.info("service2 id: {}", id);
        return "nacos2 string is " + id;
    }
}
