package com.boot3.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // HttpSecurity : 인증, 인가 API의 설정을 제공
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeHttpRequests()
                .requestMatchers("/swagger*/**").permitAll()
                .requestMatchers("/user/**").permitAll()
                .anyRequest().permitAll()

                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login").permitAll();

        return http.build();
    }

    // Password 암호화를 위한 Bean 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 더블슬래시 ( // )를 허용하기 위한 Bean 등록
    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }

    // SecurityFilterChain을 사용하기 위한 Bean 등록
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 자원에 스프링 시큐리티 필터 규칙을 적용하지 않도록 설정
        return (web) -> web.httpFirewall(defaultHttpFirewall()).ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

}
