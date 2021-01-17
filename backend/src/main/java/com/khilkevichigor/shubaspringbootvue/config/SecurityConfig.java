package com.khilkevichigor.shubaspringbootvue.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * ROLE_USER, ROLE_ADMIN - это роли пользователя, они пишутся в БД.
 * USER, ADMIN - это права пользователя, их получаем из UserDetailsService
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    //через конструктор ошибка
    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //редирект с учетом роли после логина
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/home-sss").access("hasRole('USER') or hasRole('ADMIN')")
//                .antMatchers("/user/**").access("hasRole('USER')")
//                .antMatchers("/admin/**").access("hasRole('ADMIN')")

                .anyRequest().anonymous() //для того чтобы /login не был доступен для авторизованных пользователей
                .and()
                .formLogin()
                .loginPage("/login") //страница логин недоступна авторизованным пользователям (пришлось закоментировать что б работало)
                .successHandler(myAuthenticationSuccessHandler()) //стратегия перенаправления после /login
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .and()
                //перенаправляем не на /403 а на домашнюю
                .exceptionHandling().accessDeniedPage("/home") //обработка ошибок (400)
                .and()
                .csrf()
                .disable()
        ;
    }
}

