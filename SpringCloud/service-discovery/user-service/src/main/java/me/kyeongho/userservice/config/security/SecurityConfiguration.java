package me.kyeongho.userservice.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // Password Encode를 위한 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication(인증) 설정을 위해 재정의
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // csrf 설정
                .csrf()
                    .disable()
                // h2-console 열면 프레임이 이상하게 뜨는걸 방지
                .headers()
                    .frameOptions()
                        .disable()
                .and()
                // URL path 별 접근 권한 설정
                .authorizeRequests()
                    .antMatchers("/users/**")
                        .permitAll();
    }

    // Authorization(인가) 설정을 위해 재정의
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }
}
