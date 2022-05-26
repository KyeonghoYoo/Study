package me.kyeongho.userservice.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "greeting")
@Data
public class GreetingProperties {

//    @Value("${greeting.message}")
    private String message;
}
