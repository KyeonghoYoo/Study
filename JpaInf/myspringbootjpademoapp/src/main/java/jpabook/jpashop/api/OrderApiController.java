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
	
	@GetMapping("/api/v2/orders")
	public Result<List<OrderDto>> ordersV2() {
		List<Order> orders = orderRepository.findAllByString(new OrderSearch());
		List<OrderDto> collect = orders.stream()
			.map(OrderDto::new)
			.collect(Collectors.toList());
			
		
		return new Result<List<OrderDto>>(collect.size(), collect);
	}
	
	@GetMapping("/api/v3/orders")
	public Result<List<OrderDto>> ordersV3() {
		List<Order> orders = orderRepository.findAllWithItem();
		List<OrderDto> collect = orders.stream()
			.map(OrderDto::new)
			.collect(Collectors.toList());
			
		
		return new Result<List<OrderDto>>(collect.size(), collect);
	}
	
	@GetMapping("/api/v3.1/orders")
	public Result<List<OrderDto>> ordersV3_page(
			@RequestParam(value = "offset", defaultValue = "0") int offset,
			@RequestParam(value = "limit", defaultValue = "100") int limit) {
		// 1. xToOne 관계는 fetch join으로 조회한다. (DB row 수에 영향을 주지 않는 join)
		List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
		
		
		List<OrderDto> collect = orders.stream()
			.map(OrderDto::new)
			.collect(Collectors.toList());
			
		
		return new Result<List<OrderDto>>(collect.size(), collect);
	}
	
	@GetMapping("/api/v4/orders")
	public Result<List<OrderQueryDto>> ordersV4() {
		List<OrderQueryDto> orders = orderQueryRepository.findOrderQueryDtos();
		return new Result<List<OrderQueryDto>>(orders.size(), orders);
	}
	
	@GetMapping("/api/v5/orders")
	public Result<List<OrderQueryDto>> ordersV5() {
		List<OrderQueryDto> orders = orderQueryRepository.findAllByDto_optimization();
		return new Result<List<OrderQueryDto>>(orders.size(), orders);
	}
	
	@GetMapping("/api/v6/orders")
	public Result<List<OrderFlatDto>> ordersV6() {
		List<OrderFlatDto> orders = orderQueryRepository.findAllByDto_flat();
		return new Result<List<OrderFlatDto>>(orders.size(), orders);
	}
	

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
