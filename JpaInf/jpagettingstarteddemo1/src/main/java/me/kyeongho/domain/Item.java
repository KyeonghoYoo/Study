package me.kyeongho.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item")
@Getter @Setter
public class Item {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id;
	
	private String name;
	
	private int price;
	
	@Column(name = "stock_quantity")
	private int stockQuantity;
	
	@OneToMany(mappedBy = "item")
	private List<OrderItem> orderItem = new ArrayList<>();
	
	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();
}
