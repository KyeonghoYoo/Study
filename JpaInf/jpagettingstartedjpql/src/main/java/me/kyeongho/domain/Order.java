package me.kyeongho.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import me.kyeongho.domain.valtype.Address;

@Entity
@Table(name = "orders")
@Data @Builder
public class Order {
	
	@Id @GeneratedValue
	private Long id;
	
	private int orderAmount;
	
	@Embedded
	private Address address;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	
	public Order() {
	}

	public Order(Long id, int orderAmount, Address address, Member member, Product product) {
		super();
		this.id = id;
		this.orderAmount = orderAmount;
		this.address = address;
		this.member = member;
		this.product = product;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderAmount=" + orderAmount + ", address=" + address + "]";
	}
	
}
