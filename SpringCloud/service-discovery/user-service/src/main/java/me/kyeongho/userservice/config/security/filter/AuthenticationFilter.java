package me.kyeongho.userservice.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kyeongho.userservice.config.security.JwtTokenProperties;
import me.kyeongho.userservice.dto.UserDto;
import me.kyeongho.userservice.service.UserService;
import me.kyeongho.userservice.vo.RequestLogin;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;
    private final JwtTokenProperties jwtTokenProperties;

    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, JwtTokenProperties jwtTokenProperties) {
        super(authenticationManager);
        this.userService = userService;
        this.jwtTokenProperties = jwtTokenProperties;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        try {
            RequestLogin credentials = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword(), new ArrayList<>());

            return getAuthenticationManager()
                    .authenticate(authenticationToken);

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws ServletException, IOException {
        String username = ((User) authResult.getPrincipal()).getUsername();
        UserDto userDetails = userService.getUserDetailsByEmail(username);
        log.debug("Username = {}", username);

        SecretKey secretKey = Keys.hmacShaKeyFor(jwtTokenProperties.getClientSecret().getBytes(StandardCharsets.UTF_8));
        String token = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(userDetails.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + jwtTokenProperties.getAccessExpiry()))
                .setIssuer(jwtTokenProperties.getIssuer())
                .setIssuedAt(new Date())
                .claim("email", userDetails.getEmail())
                .claim("name", userDetails.getName())
                .claim("userId", userDetails.getUserId())
                .claim("roles", new ArrayList<>())
                .signWith(secretKey)
                .compact();

        response.setHeader("token", token);
        response.setHeader("userId", userDetails.getUserId());

        chain.doFilter(request, response);
    }
}
