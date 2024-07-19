package com.brand0nny.springboot.web.abarrotes_tepari.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        String[] resources = new String[] {
            "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**"
};
        return http
        .csrf(csrf -> csrf
        .disable())
        .authorizeHttpRequests(authorize -> authorize
        .requestMatchers(resources).permitAll()
        .requestMatchers("/","/login").permitAll()
        .anyRequest().authenticated())
        .formLogin(form -> form
        .loginPage("/login")
        .defaultSuccessUrl("/home"))
        .logout(logout -> logout
        .permitAll()
        .logoutSuccessUrl("/login?logout"))
        .build();
        


        
    }



}
