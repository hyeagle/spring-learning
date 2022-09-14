package com.learning.nacos1.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author hongyu
 */
@Component
public class MyCommandLineRunner2 implements CommandLineRunner, Ordered {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("my command runner 2 run");
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
