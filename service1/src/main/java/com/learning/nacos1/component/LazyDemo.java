package com.learning.nacos1.component;

import com.learning.nacos1.pojo.BeanDemo1;
import com.learning.nacos1.pojo.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import zipkin2.Endpoint;


/**
 * @author hongyu
 */
@Configuration
public class LazyDemo {

//    static {
//        System.out.println("lazy demo static");
//    }
//
//    public LazyDemo() {
//        System.out.println("create lazy demo");
//    }

    @Bean
    public BeanDemo1 getBeanDemo1() {
        return new BeanDemo1();
    }

//    public void begin() {
//    }
}
