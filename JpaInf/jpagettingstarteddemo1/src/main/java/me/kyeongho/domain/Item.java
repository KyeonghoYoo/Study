package me.kyeongho.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Getter @Setter
public abstract class Item extends BaseEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id;
	
	private String name;
	
	private int price;
	
	@Column(name = "stock_quantity")
	private int stockQuantity;
	
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private List<OrderItem> orderItem = new ArrayList<>();
	
	@ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
	private List<Category> categories = new ArrayList<>();
}
