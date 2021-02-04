package me.kyeongho.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("kyeongho.api.naver.client")
@Data
public class NaverApiClientProperties {

	private String id;
	
	private String sceret;
	
	private String movieurl;
}
