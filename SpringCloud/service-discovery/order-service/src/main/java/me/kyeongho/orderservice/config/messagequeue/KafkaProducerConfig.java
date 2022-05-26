package me.kyeongho.orderservice.config.messagequeue;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@EnableKafka
@Configuration
@EnableConfigurationProperties(EcommerceKafkaProperties.class)
public class KafkaProducerConfig {

    private final EcommerceKafkaProperties ecommerceKafkaProperties;

    public KafkaProducerConfig(EcommerceKafkaProperties properties) {
        this.ecommerceKafkaProperties = properties;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        HashMap<String, Object> properties = new HashMap<>();
        /* 카프카 환경설정, 이 또한 외부 설정에서 관리되는 것이 좋다. */
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ecommerceKafkaProperties.getServer().getUrl());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
