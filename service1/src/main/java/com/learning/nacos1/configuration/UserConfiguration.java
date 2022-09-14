package com.learning.nacos1.configuration;

import com.learning.nacos1.pojo.User;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hongyu
 */

@ConfigurationProperties(prefix = "user")
@Configuration
@Data
public class UserConfiguration {
    private String name;

    private Integer age;

    @Bean
    public User getUser() {
        return new User(name, age);
    }
}
