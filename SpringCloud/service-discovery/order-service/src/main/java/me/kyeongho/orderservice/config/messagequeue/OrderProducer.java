package me.kyeongho.orderservice.config.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kyeongho.orderservice.dto.KafkaOrderDto;
import me.kyeongho.orderservice.dto.OrderDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaOrderDto send(String topic, OrderDto orderDto) {
        KafkaOrderDto kafkaOrderDto = new KafkaOrderDto(topic, orderDto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(kafkaOrderDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonString);

        log.info("Kafka Order Producer sent data from the Order Microservice: {}", kafkaOrderDto);

        return kafkaOrderDto;
    }
}
