package com.Travelbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class Securityconfig {
    private JWTRequestFilter jwtToken;

    public Securityconfig(JWTRequestFilter jwtToken) {
        this.jwtToken = jwtToken;
    }
    @Bean
    public SecurityFilterChain sfcSecur ity(HttpSecurity http) throws Exception{

        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtToken, AuthorizationFilter.class);
        http.authorizeHttpRequests().anyRequest().permitAll();
//        http.authorizeHttpRequests().
//                requestMatchers("/Api/v1/signup/adduser","/Api/v1/signup/Login").permitAll().
//                requestMatchers("/Api/v1/country/countries").hasAnyRole("ADMIN","USER").
//                anyRequest().authenticated();

        return http.build();
    }
}

