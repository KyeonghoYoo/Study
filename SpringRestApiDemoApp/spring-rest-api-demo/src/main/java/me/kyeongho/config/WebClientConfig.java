package me.kyeongho.config;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunction.ofRequestProcessor;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunction.ofResponseProcessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import me.kyeongho.common.utill.ThrowingConsumer;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Configuration
public class WebClientConfig {

	@Bean
	WebClient webClient() {
		// MaxInMemorySize 설정
		ExchangeStrategies exchangeStrategies =
					ExchangeStrategies
							.builder()
							.codecs(configurer -> configurer.defaultCodecs()
																.maxInMemorySize(1024*1024*50))
							.build();
		// Logging 설정
		exchangeStrategies
				.messageWriters().stream()
				.filter(LoggingCodecSupport.class::isInstance)
				.forEach(writer -> ((LoggingCodecSupport) writer).setEnableLoggingRequestDetails(true));
		
		
		
		return WebClient.builder()
						// HttpClient TimeOut 설정
						/*
						 * HTTPS 인증서를 검증하지 않고 바로 접속하는 설정과,
						 * TCP 연결 시 ConnectionTimeOut , ReadTimeOut , WriteTimeOut 을 적용하는 설정을 추가하였습니다.
						 * 
						 * Stream 처리에서 Exception 처리를 위한 Util인 ThrowingConsumer는 아래를 참고
						 * https://github.com/Odysseymoon/spring-webflux-template/blob/master/src/main/java/moon/odyssey/webflux/utils/lambda/ThrowingConsumer.java
						 */
						.clientConnector(new ReactorClientHttpConnector(
								HttpClient.create()
										.secure(ThrowingConsumer.unchecked(sslConxtextSpec -> 
													sslConxtextSpec.sslContext(SslContextBuilder.forClient()
															.trustManager(InsecureTrustManagerFactory.INSTANCE)
															.build())))
										.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 120_000)
										.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(180))
																	.addHandler(new WriteTimeoutHandler(180)))))
						.exchangeStrategies(exchangeStrategies)
						// filter를 통해 Request와 Response의 로깅을 수행하도록 로직 구현
						.filter(ofRequestProcessor(
									clientRequest -> {
										log.debug("Request: {} {}", clientRequest.method(), clientRequest.url());
										clientRequest.headers()
												.forEach((name, values) -> 
															values.forEach(value -> log.debug("{} : {}", name, value)));
										return Mono.just(clientRequest);
									}
								))
						.filter(ofResponseProcessor(clientResponse -> {
							clientResponse.headers()
									.asHttpHeaders()
									.forEach((name, values) -> values.forEach(value -> log.debug("{} : {}", name, value)));
							return Mono.just(clientResponse);
						}))
						.build();
	}
}
