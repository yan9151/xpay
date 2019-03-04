package com.xpay.admin.console.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  定义当需要用户登录时候，转到的登录页面。
        http.formLogin()
                // 设置登录页面
                .loginPage("/login.html").loginProcessingUrl("/").successForwardUrl("/index.html")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                // 定义哪些URL需要被保护、哪些不需要被保护
                .authorizeRequests()
                .antMatchers("/css/**", "/script/**", "/images/**", "/lay/**", "/font/**"
                        , "/layui.js", "/login.html", "/actuator/info")
                .permitAll()     // 设置所有人都可以访问登录页面
                .anyRequest()               // 任何请求,登录后可以访问
                .authenticated()
                .and()
                .headers().frameOptions().sameOrigin() // 运行增加到 IFrame
                .and()
                .csrf().disable(); //关闭csrf防护

        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login.html");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
