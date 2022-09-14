package com.learning.nacos1.comditionbean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongyu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeanCreate {

    public String name;

    public void print() {
        System.out.println("bean create: " + name);
    }
}
