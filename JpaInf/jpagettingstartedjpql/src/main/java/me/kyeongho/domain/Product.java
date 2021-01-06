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
}
