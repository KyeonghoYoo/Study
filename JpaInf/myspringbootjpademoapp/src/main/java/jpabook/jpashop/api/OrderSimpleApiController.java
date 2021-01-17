package jpabook.jpashop.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderStatus;
import jpabook.jpashop.domain.valtype.Address;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQeuryRepository;
import jpabook.jpashop.repository.order.simplequery.SimpleOrderQueryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * XtoOne 관계만 포함됨
 * Order Order -> Member Order -> Delivery
 *
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

	private final OrderRepository orderRepository;

	private final OrderSimpleQeuryRepository orderSimpleQueryRepository;
	
	/**
	 * 엔티티를 바로 반환 시, Jackson이 제공하는 라이브러리를 이용하여 LAZY로딩 무한 루프 해결
	 */
	@GetMapping("/api/v1/simple-orders")
	public List<Order> ordersv1() {
		List<Order> all = orderRepository.findAllByString(new OrderSearch());
		for (Order order : all) {
			order.getMember().getUsername(); // Lazy 강제 초기화
			order.getDelivery().getAddress(); // Lazy 강제 초기화
		}
		return all;
	}

	/**
	 * DTO를 사용한 LAZY 로딩 루프 문제 해결
	 * 또한 API Sepc 변경에 대한 운영 부담도 줄어든다.
	 */
	@GetMapping("/api/v2/simple-orders")
	public Result<List<SimpleOrderDto>> orderV2() {
		List<Order> orders = orderRepository.findAllByString(new OrderSearch());
		
		List<SimpleOrderDto> result = orders.stream()
				.map(SimpleOrderDto::new)
				.collect(Collectors.toList());
		
		return new Result<List<SimpleOrderDto>>(result.size(), result);
	}
	
	/**
	 * fetch join을 이용한 N + 1 문제 해결
	 */
	@GetMapping("/api/v3/simple-orders")
	public Result<List<SimpleOrderDto>> orderV3() {
		List<Order> orders = orderRepository.findAllWithMemberDelivery();
		
		List<SimpleOrderDto> result = orders.stream()
				.map(SimpleOrderDto::new)
				.collect(Collectors.toList());
		
		return new Result<List<SimpleOrderDto>>(result.size(), result);
	}
	
	/**
	 * JPA에서 DTO로 바로 조회
	 */
	@GetMapping("/api/v4/simple-orders")
	public Result<List<SimpleOrderQueryDto>> orderV4() {
		List<SimpleOrderQueryDto> resultList = orderSimpleQueryRepository.findOrderDtos();
		

		return new Result<List<SimpleOrderQueryDto>>(resultList.size(), resultList);
	}
	
	@Data
	@AllArgsConstructor
	static class Result<T> {
		
		private int count;
		
		private T data;
	}
	
	@Data
	static public class SimpleOrderDto {
		
		private Long orderId;
		
		private String name;
		
		private LocalDateTime orderDate;
		
		private OrderStatus orderStatus;
		
		private Address address;
		
		public SimpleOrderDto(Order order) {
			this.orderId = order.getId();
			this.name = order.getMember().getUsername();
			this.orderDate = order.getOrderDate();
			this.orderStatus = order.getStatus();
			this.address = order.getDelivery().getAddress();
		}
	}
}
