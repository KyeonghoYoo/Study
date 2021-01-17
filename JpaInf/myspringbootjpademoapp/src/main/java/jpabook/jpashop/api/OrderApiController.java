package jpabook.jpashop.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderStatus;
import jpabook.jpashop.domain.orderitem.OrderItem;
import jpabook.jpashop.domain.valtype.Address;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.query.OrderFlatDto;
import jpabook.jpashop.repository.order.query.OrderQueryDto;
import jpabook.jpashop.repository.order.query.OrderQueryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

	private final OrderRepository orderRepository;
	
	private final OrderQueryRepository orderQueryRepository;
	
	// 엔티티를 직접 노출시키는 방법
	@GetMapping("/api/v1/orders")
	public List<Order> ordersV1() {
		List<Order> all = orderRepository.findAllByString(new OrderSearch());
		for (Order order : all) {
			order.getMember().getUsername();
			order.getDelivery().getAddress();
			
			List<OrderItem> orderItems = order.getOrderItems();
			orderItems.stream().forEach(o -> o.getItem().getName());
		}
		
		return all;
	}
	
	// Enitity를 DTO로 변환하여 리턴하되, 연관관계 프록시 객체에 대해서는 lazy loading으로 초기화
	@GetMapping("/api/v2/orders")
	public Result<List<OrderDto>> ordersV2() {
		List<Order> orders = orderRepository.findAllByString(new OrderSearch());
		List<OrderDto> collect = orders.stream()
			.map(OrderDto::new)
			.collect(Collectors.toList());
			
		
		return new Result<List<OrderDto>>(collect.size(), collect);
	}
	
	// 페치조인으로 한 번의 쿼리로 모든 데이터를 처리함. 단점은 페이징이 불가능함.
	@GetMapping("/api/v3/orders")
	public Result<List<OrderDto>> ordersV3() {
		List<Order> orders = orderRepository.findAllWithItem();
		List<OrderDto> collect = orders.stream()
			.map(OrderDto::new)
			.collect(Collectors.toList());
			
		
		return new Result<List<OrderDto>>(collect.size(), collect);
	}
	
	// ToOne 관계에 대해서는 fetch 조인을하고, 컬렉션 연관관계에 대해서는 hibernate.default_batch_fetch_size 설정을 통해
	// IN 쿼리를 날리도록하여 성능 최적화. 이렇게하면 페치조인을 사용하면서 페이징이 가능해짐.
	@GetMapping("/api/v3.1/orders")
	public Result<List<OrderDto>> ordersV3_page(
			@RequestParam(value = "offset", defaultValue = "0") int offset,
			@RequestParam(value = "limit", defaultValue = "100") int limit) {
		// 1. xToOne 관계는 fetch join으로 조회한다. (DB row 수에 영향을 주지 않는 join)
		List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
		
		// hibernate.default_batch_fetch_size나 @BatchSize 설정을 통해 IN 쿼리를 발생시켜 쿼리 성능 최적화
		List<OrderDto> collect = orders.stream()
			.map(OrderDto::new)
			.collect(Collectors.toList());
			
		
		return new Result<List<OrderDto>>(collect.size(), collect);
	}
	
	//// 여기부터 JPQL에 DTO로 직접 조회하는 방법
	// ToOne 관계에 대해서는 join 하여 DTO에 담고 컬렉션 값에 대해서는 lazy loading을 시키는 방법
	@GetMapping("/api/v4/orders")
	public Result<List<OrderQueryDto>> ordersV4() {
		List<OrderQueryDto> orders = orderQueryRepository.findOrderQueryDtos();
		return new Result<List<OrderQueryDto>>(orders.size(), orders);
	}
	
	// 인메모리에 FK를 Collection으로 만들어 IN 쿼리를 쏘는 방법
	@GetMapping("/api/v5/orders")
	public Result<List<OrderQueryDto>> ordersV5() {
		List<OrderQueryDto> orders = orderQueryRepository.findAllByDto_optimization();
		return new Result<List<OrderQueryDto>>(orders.size(), orders);
	}
	
	// DTO를 직접 사용하여 flat하게 데이터를 조회받는 방법
	@GetMapping("/api/v6/orders")
	public Result<List<OrderFlatDto>> ordersV6() {
		List<OrderFlatDto> orders = orderQueryRepository.findAllByDto_flat();
		return new Result<List<OrderFlatDto>>(orders.size(), orders);
	}
	
	// Flat DTO로 조회한 다음 개발자가 직접 어플리케이션단 로직으로 원하는 Response로 정재하는 방법
	@GetMapping("/api/v6.1/orders")
	public Result<List<OrderQueryDto>> ordersV6_flat2() {
		List<OrderQueryDto> orders = orderQueryRepository.findAllByDto_flat2();
		return new Result<List<OrderQueryDto>>(orders.size(), orders);
	}
	
	@Data
	@AllArgsConstructor
	static class Result<T> {
		
		private int count;
		
		private T data;
	}
	
	@Data
	static class OrderDto {
		
		private Long orderId;
		
		private String name;
		
		private LocalDateTime orderDate;
		
		private OrderStatus orderStatus;
		
		private Address address;
		
		private List<OrderItemDto> orderItem = new ArrayList<>();
		
		public OrderDto(Order order) {
			this.orderId = order.getId();
			this.name = order.getMember().getUsername();
			this.orderDate = order.getOrderDate();
			this.orderStatus = order.getStatus();
			this.address = order.getDelivery().getAddress();
			this.orderItem = order.getOrderItems().stream()
					.map(OrderItemDto::new)
					.collect(Collectors.toList());
		}
	}
	
	@Data
	static class OrderItemDto {
		
		private String itemName;
		private int orderPrice;
		private int count;
		
		public OrderItemDto(OrderItem orderItem) {
			this.itemName = orderItem.getItem().getName();
			this.orderPrice = orderItem.getOrderPrice();
			this.count = orderItem.getCount();
		}
	}
}
