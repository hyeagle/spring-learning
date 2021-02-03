package com.learning.nacos1.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ross-nacos2")
public interface Nacos2Integration {

    @GetMapping("nacos2/api/{id}")
    String getNacos2(@PathVariable int id);
}
