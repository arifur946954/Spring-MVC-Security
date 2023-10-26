package com.SecurityMVC.Security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
public class DemoSecurityConfig {
@Bean
public UserDetailsManager userDetailsManager(DataSource dataSource){
    //define a query artrinve user by uer name
    JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
    jdbcUserDetailsManager.setUsersByUsernameQuery(
            "select user_id,pw, active from members where user_id=?");
    //define a quety retrive roles/authorities by roles
    jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "select user_id,role from roles where user_id=?");
    return jdbcUserDetailsManager;

}
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers("/").hasAnyRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasAnyRole("MANAGER")
                        .requestMatchers("/systems/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated())

                .formLogin(form->form
                        .loginPage("/showMyLoginPages")
                        .loginProcessingUrl("/authenticationTheUser")
                        .permitAll()
                )
                .logout(logout->logout.permitAll()
                )
                .exceptionHandling(configurer->
                        configurer.accessDeniedPage("/access-denied")
                );

        return http.build();
    }

    //    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails john= User.builder()
//                .username("john")
//                .password("{noop}123456")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails mary= User.builder()
//                .username("mary")
//                .password("{noop}123456")
//                .roles("EMPLOYEE","MANAGER")
//                .build();
//
//        UserDetails susan= User.builder()
//                .username("susan")
//                .password("{noop}123456")
//                .roles("EMPLOYEE","MANAGER","ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john,mary,susan);
//    }

}
