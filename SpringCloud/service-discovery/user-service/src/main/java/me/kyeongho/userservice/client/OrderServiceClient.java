package me.kyeongho.userservice.client;

import me.kyeongho.userservice.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

//    @RequestMapping(path = "/order-service/{userId}/orders")
    @RequestMapping(path = "/order-service/{userId}/orders_ng") // 잘못된 api path 호출
    List<ResponseOrder> getOrders(@PathVariable String userId);

}
