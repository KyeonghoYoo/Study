package me.kyeongho.userservice.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 스프링 설정파일로부터 "jwt.token" 이하의 값들을 바인딩 받아 불러온다.
 *
 * @author 유경호 ykh6242@gmail.com
 * @since 2022.04.26
 */
@Component
@ConfigurationProperties(prefix = "jwt.token")
@Data
public class JwtTokenProperties {

	private String header;
	private String issuer;
	private String clientSecret;
	private Long accessExpiry;
	private String[] ignoreUrl;
}
