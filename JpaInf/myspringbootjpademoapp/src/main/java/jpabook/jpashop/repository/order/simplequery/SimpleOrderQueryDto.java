package jpabook.jpashop.repository.order.simplequery;

import java.time.LocalDateTime;

import jpabook.jpashop.domain.order.OrderStatus;
import jpabook.jpashop.domain.valtype.Address;
import lombok.Data;

@Data
public class SimpleOrderQueryDto {
	
	private Long orderId;
	
	private String name;
	
	private LocalDateTime orderDate;
	
	private OrderStatus orderStatus;
	
	private Address address;
	
	public SimpleOrderQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
		this.orderId = orderId;
		this.name = name;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.address = address;
	}
}
