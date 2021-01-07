package me.kyeongho.domain.valtype;

import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Embeddable
@Getter @ToString @EqualsAndHashCode @Builder
public class Address {

	private String city;
	
	private String street;
	
	private String zipcode;
	
	public Address() {
	}

	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
}
