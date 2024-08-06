package com.brand0nny.springboot.web.abarrotes_tepari.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.brand0nny.springboot.web.abarrotes_tepari.config.jwt.JwtAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {
        @Autowired
        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        @Autowired
        private final AuthenticationProvider authProvider;

        String[] resources = new String[] {
                        "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**"
        };


         @Configuration
         public class WebConfig implements WebMvcConfigurer {
                 @Override
                 public void addCorsMappings(CorsRegistry registry) {
                         registry.addMapping("/**")
                                         .allowedOrigins("http://localhost:3000") 
                                         .allowedMethods("*")
                                         .allowedHeaders("*")
                                         .allowCredentials(true);
                 }
         }

        
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http

                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(resources).permitAll()
                                                .requestMatchers("/auth/**", "/home/**","/products/**").permitAll()
                                                .anyRequest().authenticated())
                                .sessionManagement(sessionManagement -> sessionManagement
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                // .formLogin(login->login
                                // .loginPage("/auth/login")
                                // .successForwardUrl("/api/v/demo"))
                                .authenticationProvider(authProvider)
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();

        }

}