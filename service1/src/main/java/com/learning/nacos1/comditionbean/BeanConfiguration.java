package com.learning.nacos1.comditionbean;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author hongyu
 */
@AutoConfigureBefore(BeanConditionConfig.class)
@Configuration
public class BeanConfiguration {

    @Bean
    @ConditionalOnClass(name = "com.learning.nacos1.component.LazyDemo1") // false
    public BeanCondition createBeanCondition1() {
        return new BeanCondition("first");
    }


//    @Bean
//    @ConditionalOnMissingBean(value = {BeanCondition.class})
//    public BeanCondition createBeanCondition2() {
//        return new BeanCondition("second");
//    }

//    @Bean
//    @ConditionalOnMissingBean(value = {BeanCondition.class})
//    public BeanCreate beanCreate() {
//        return new BeanCreate("missing");
//    }
}
