package com.example.security.configuration;

import com.example.security.filter.ValidateCodeFilter;
import com.example.security.handler.MyAuthenticationAccessDeniedHandler;
import com.example.security.handler.MyAuthenticationFailureHandler;
import com.example.security.handler.MyAuthenticationSuccessHandler;
import com.example.security.handler.MyLogoutSuccessHandler;
import com.example.security.strategy.MySessionExpiredStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // httpSecurity.httpBasic()  // http basic 方式
        httpSecurity.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)   // 添加验证码校验过滤器
                .formLogin()  // 表单方式
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .authorizeRequests()  // 授权配置
                .antMatchers("/login.html", "/code/image").permitAll()
                .anyRequest()  // 所有请求
                .authenticated()  // 都需要认证
                .and()
                .sessionManagement()
                .invalidSessionUrl("/session/invalid")
                .maximumSessions(1)   // 限制同一时间账号登陆个数
                .expiredSessionStrategy(sessionExpiredStrategy)
                .and()
                .and().logout()
                .logoutUrl("/signout")
                //.logoutSuccessUrl("/signout/success")
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and().exceptionHandling().accessDeniedHandler(myAuthenticationAccessDeniedHandler)
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Resource
    private ValidateCodeFilter validateCodeFilter;

    @Resource
    private MySessionExpiredStrategy sessionExpiredStrategy;

    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Resource
    private MyAuthenticationAccessDeniedHandler myAuthenticationAccessDeniedHandler;

}
