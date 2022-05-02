package me.kyeongho.userservice.config.security;

import lombok.RequiredArgsConstructor;
import me.kyeongho.userservice.config.security.filter.AuthenticationFilter;
import me.kyeongho.userservice.service.UserService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(JwtTokenProperties.class)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final Environment env;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProperties jwtTokenProperties;

    // AuthenticationFilter 빈 획득
    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        return new AuthenticationFilter(
                authenticationManager(),
                userService,
                jwtTokenProperties);
    }

    // Authorization(인가) 설정을 위해 재정의
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
                    .antMatchers("/actuator/**")
                        .permitAll()
                    .antMatchers("/users/**")
                        .hasIpAddress("10.10.100.145")
                .and()
                .addFilter(getAuthenticationFilter());
    }

    // Authentication(인증) 설정을 위해 재정의
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }
}
