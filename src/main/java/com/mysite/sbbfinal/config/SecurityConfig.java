package com.mysite.sbbfinal.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("security config ...");

        http	
        		// .csrf(csrf -> csrf.disable()) // 개발 중 테스트 목적으로 비활성화, 데이터베이스에 데이터 저장 가능, CSRF토큰 설정시 활성화하면 500에러 발생! 
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/home", "/signup", "/error").permitAll()
                        .requestMatchers("/js/**", "/css/**", "/favicon.*", "/api/**").permitAll()
                        .requestMatchers("/users", "/user/*/roles", "/user/*/role/**").hasRole("ADMIN")  // 관리자만 사용자 목록 접근 가능
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
