package me.kyeongho.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import me.kyeongho.domain.valuetype.Address;

@Entity
@Getter @Setter
public class Delivery extends BaseEntity {

	@Id @GeneratedValue
	private Long id;
	
	@Embedded
	private Address address;
	
	private DeliveryStatus status;
	
	@OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
	private Order order;
}
