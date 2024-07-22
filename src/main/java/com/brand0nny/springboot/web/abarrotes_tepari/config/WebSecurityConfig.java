package com.brand0nny.springboot.web.abarrotes_tepari.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    String[] resources = new String[] {
            "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf
                        .disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(resources).permitAll()
                        .requestMatchers("/", "/login","/register","/add-address","/add-address/**","/home","/home/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/home")
                        .failureUrl("/login?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password")

                )
                .logout(logout -> logout
                        .permitAll()
                        .logoutSuccessUrl("/login?logout"))
                .build();

    }

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }

    @Autowired
    org.springframework.security.core.userdetails.UserDetailsService UserDetailsService;

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(UserDetailsService).passwordEncoder(passwordEncoder());

    }
}