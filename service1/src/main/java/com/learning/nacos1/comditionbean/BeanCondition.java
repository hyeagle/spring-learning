package com.learning.nacos1.comditionbean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongyu
 */
public class BeanCondition {

    private String name;

    public BeanCondition() {
        System.out.println("bean condition new");
    }

    public BeanCondition(String name) {
        System.out.println("bean condition name: " + name);
    }

    public void print() {
        System.out.println("bean condition: " + name);
    }
}
