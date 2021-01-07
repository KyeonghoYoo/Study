package me.kyeongho.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "product")
@Data @Builder
public class Product {

	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	private int price;
	
	private int stockAmount;
	
	@OneToMany(mappedBy = "product")
	private List<Order> orders = new ArrayList<>();
	
	public Product() {
	}

	public Product(Long id, String name, int price, int stockAmount, List<Order> orders) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stockAmount = stockAmount;
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", stockAmount=" + stockAmount + "]";
	}
	
	
}
