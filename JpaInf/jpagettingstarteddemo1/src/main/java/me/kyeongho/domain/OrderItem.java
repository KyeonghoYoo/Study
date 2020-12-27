package me.kyeongho.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORDER_ITEM")
@Getter @Setter
public class OrderItem {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ITEM_ID")
	private Long id;
	
	@Column(name = "ORDER_ID")
	private Long orderId;
	
	@Column(name = "ITEM_ID")
	private Long itemId;
	
	private int orderprice;
	
	private int count;
}
