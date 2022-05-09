package me.kyeongho.catalogservice.config.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kyeongho.catalogservice.entity.CatalogEntity;
import me.kyeongho.catalogservice.repository.CatalogRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Transactional(readOnly = true)
@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CatalogRepository catalogRepository;

    @Transactional
    @KafkaListener(topics = "example-catalog-topic")
    public void updateQty(String kafkaMessage) {
        log.info("Kafka Message: -> {}", kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String productId = String.valueOf(map.get("productId"));
        CatalogEntity catalogEntity = catalogRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("wrong productId: %s", productId)));
        catalogEntity.setStock(catalogEntity.getStock() - (Integer) map.get("qty"));

    }
}
