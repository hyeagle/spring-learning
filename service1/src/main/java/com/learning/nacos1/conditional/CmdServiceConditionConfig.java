package com.learning.nacos1.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author hongyu
 */
@Configuration
public class CmdServiceConditionConfig {

    @Bean
    @Conditional(MacConditional.class)
    public CmdService window() {
        return new MacCmdService();
    }

    @Bean
    @Conditional(LinuxConditional.class)
    public CmdService linux() {
        return new LinuxCmdService();
    }
}
