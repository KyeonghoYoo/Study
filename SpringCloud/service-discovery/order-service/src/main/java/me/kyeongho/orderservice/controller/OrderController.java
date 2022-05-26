package me.kyeongho.orderservice.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kyeongho.orderservice.config.messagequeue.KafkaProducer;
import me.kyeongho.orderservice.config.messagequeue.OrderProducer;
import me.kyeongho.orderservice.dto.OrderDto;
import me.kyeongho.orderservice.entity.OrderEntity;
import me.kyeongho.orderservice.service.OrderService;
import me.kyeongho.orderservice.vo.RequestOrder;
import me.kyeongho.orderservice.vo.ResponseOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/order-service/")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final Environment env;
    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;
    private final OrderProducer orderProducer;

    @GetMapping(path = "/health_check")
    @Timed(value = "orders.status", longTask = true)
    public String status() {
        return String.format("it's Working in User Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping(path = "/welcome")
    @Timed(value = "orders.welcome", longTask = true)
    public String welcome() {
        return env.getProperty("greeting.message");
    }

    @PostMapping(path = "/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(
            @PathVariable(name = "userId") String userId,
            @RequestBody RequestOrder requestOrder
    ) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = mapper.map(requestOrder, OrderDto.class);
        orderDto.setUserId(userId);

        /* JPA WORKING */
        OrderDto createdOrderDto = orderService.createOrder(orderDto);
        ResponseOrder responseOrder = mapper.map(createdOrderDto, ResponseOrder.class);

        /* KAFKA CONNECTOR WORKING */
//        orderDto.setOrderId(UUID.randomUUID().toString());
//        orderDto.setTotalPrice(orderDto.getUnitPrice() * orderDto.getQty());
//        orderProducer.send("orders", orderDto);
//        ResponseOrder responseOrder = mapper.map(orderDto, ResponseOrder.class);

        /* SEND AN ORDER TO KAFKA */
        kafkaProducer.send("example-catalog-topic", orderDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseOrder);
    }

    @GetMapping(path = "/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable(name = "userId") String userId) {
        log.info("Before retrieve orders data");
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        Iterable<OrderEntity> orderEntityIterable = orderService.getAllOrdersByUserId(userId);
        log.info("After retrieve orders data");
        ArrayList<ResponseOrder> responseOrders = new ArrayList<>();

        orderEntityIterable
                .forEach(orderEntity -> responseOrders.add(mapper.map(orderEntity, ResponseOrder.class)));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseOrders);
    }
}
