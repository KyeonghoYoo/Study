package me.kyeongho.apigatewayservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import me.kyeongho.apigatewayservice.JwtProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
    
    private final Environment environment;
    private final JwtProperties jwtProperties;

    public AuthorizationHeaderFilter(Environment environment, JwtProperties jwtProperties) {
        super(Config.class);
        this.environment = environment;
        this.jwtProperties = jwtProperties;
    }

    public static class Config {
        
    }

    // login -> token -> users (wtih token) -> header (include token)
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
//            ServerHttpResponse response = exchange.getResponse();
            
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "no authorization header exists", HttpStatus.UNAUTHORIZED);
            }
            
            String authorizationHeader = Objects.requireNonNull(request.getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
            String jwt = authorizationHeader.replace("Bearer", "");
            
            if (!isJwtValid(jwt)) {
                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }
            
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                
            }));
        };

    }

    private boolean isJwtValid(String jwt) {
        SecretKey secretKey;
        try {
            secretKey = Keys.hmacShaKeyFor(jwtProperties.getClientSecret().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("Invalid Client Secret Key", e);
            return false;
        }
        JwtParser parser = Jwts.parserBuilder()
                .requireIssuer(jwtProperties.getIssuer())
                .setSigningKey(secretKey)
                .build();
        Jws<Claims> claimsJws;
        try {
            claimsJws = parser.parseClaimsJws(jwt);
        } catch (Exception e) {
            log.error("Invalid JWT Token", e);
            return false;
        }
        String subject = claimsJws.getBody().getSubject();

        return !Objects.isNull(subject) && !StringUtils.isEmpty(subject);
    }

    // Mono, Flux -> Spring WebFlux
    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        log.error(message);
        return response.setComplete();
    }
}
