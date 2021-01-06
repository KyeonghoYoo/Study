package me.kyeongho.domain.valtype;

import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Data;

@Embeddable
@Data @Builder
public class Address {

	private String city;
	
	private String street;
	
	private String zipcode;
}
