package me.kyeongho.orderservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
public class KafkaOrderDto implements Serializable {

    private Schema schema;
    private Payload payload;

    public KafkaOrderDto(String topic, OrderDto orderDto) {
        List<Field> fields = Arrays.asList(
                new Field("string", true, "order_id"),
                new Field("string", true, "user_id"),
                new Field("string", true, "product_id"),
                new Field("int32", true, "qty"),
                new Field("int32", true, "unit_price"),
                new Field("int32", true, "total_price")
        );
        this.schema = Schema.builder()
                .type("struct")
                .fields(fields)
                .optional(false)
                .name(topic)
                .build();
        this.payload = Payload.builder()
                .order_id(orderDto.getOrderId())
                .user_id(orderDto.getUserId())
                .product_id(orderDto.getProductId())
                .qty(orderDto.getQty())
                .unit_price(orderDto.getUnitPrice())
                .total_price(orderDto.getTotalPrice())
                .build();
    }
}
