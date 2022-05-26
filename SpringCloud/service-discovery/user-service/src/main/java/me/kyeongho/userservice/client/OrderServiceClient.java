package me.kyeongho.userservice.client;

import me.kyeongho.userservice.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

    /**
     * 페인 클라이언트의 단점은 페인 클라이언트를 활용하기 위해서는 요청할 서비스를 개발자가 잘 알고 있어야한다는 점에 있음.
     * 인터페이스를 정의했으나, 반환되는 타입, 주어질 파라미터, 메서드 이름만 가지고는 정확히 상대 api가 어떠한 행위를 수행하는지 알 수 없음.
     * @param userId
     * @return
     */
//    @RequestMapping(path = "/order-service/{userId}/orders")
    @RequestMapping(path = "/order-service/{userId}/orders") // 잘못된 api path 호출
    List<ResponseOrder> getOrders(@PathVariable String userId);

}
