package com.example.caloriestracking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/error", "/webjars/**", "/swagger-ui/**", "/v3/api-docs/**"
                        , "/auth/login", "/auth/logout", "/auth/forgotpass", "/auth/reset", "/auth/feedback/**"
                        ,"/foods/**", "/users/**", "/track/**"
                ).permitAll()   //
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .and()
                .csrf().disable() // Vô hiệu hóa CSRF nếu không cần thiết
                .formLogin().disable() // Vô hiệu hóa form login nếu không cần thiết
                .httpBasic().disable(); // Vô hiệu hóa HTTP basic authentication nếu không cần thiết
                ;
        return http.build();
    }
}
