package me.kyeongho.orderservice.controller;

import lombok.RequiredArgsConstructor;
import me.kyeongho.orderservice.config.messagequeue.KafkaProducer;
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

@RestController
@RequestMapping(path = "/order-service/")
@RequiredArgsConstructor
public class OrderController {

    private final Environment env;
    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;

    @GetMapping(path = "/health_check")
    public String status() {
        return String.format("it's Working in User Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping(path = "/welcome")
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

        /* JPA WORKING */
        OrderDto orderDto = mapper.map(requestOrder, OrderDto.class);
        orderDto.setUserId(userId);
        OrderDto createdOrderDto = orderService.createOrder(orderDto);

        ResponseOrder responseOrder = mapper.map(createdOrderDto, ResponseOrder.class);

        /* SEND THIS ORDER TO KAFKA */
        kafkaProducer.send("example-catalog-topic", createdOrderDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseOrder);
    }

    @GetMapping(path = "/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable(name = "userId") String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        Iterable<OrderEntity> orderEntityIterable = orderService.getAllOrdersByUserId(userId);

        ArrayList<ResponseOrder> responseOrders = new ArrayList<>();

        orderEntityIterable
                .forEach(orderEntity -> responseOrders.add(mapper.map(orderEntity, ResponseOrder.class)));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseOrders);
    }
}
