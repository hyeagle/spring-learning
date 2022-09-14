package com.learning.nacos1.comditionbean;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author hongyu
 */
@Configuration
public class BeanConditionConfig {

    @Bean
    @ConditionalOnMissingBean(value = {BeanCondition.class})
    public BeanCreate beanCreate() {
        return new BeanCreate("missing");
    }
}
