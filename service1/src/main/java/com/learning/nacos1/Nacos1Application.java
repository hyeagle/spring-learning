package com.learning.nacos1;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.ClassUtils;

import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Nacos1Application {

    public static void main(String[] args) {
        SpringApplication.run(Nacos1Application.class, args);
    }

}
