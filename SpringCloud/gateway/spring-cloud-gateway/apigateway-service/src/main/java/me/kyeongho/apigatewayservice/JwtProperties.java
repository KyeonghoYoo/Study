package me.kyeongho.apigatewayservice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt.token")
@Data
public class JwtProperties {

    private String header;
    private String issuer;
    private String clientSecret;
    private String accessExpiry;
    private String ignoreUrl;
}
