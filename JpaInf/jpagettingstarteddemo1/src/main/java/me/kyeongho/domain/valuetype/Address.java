package me.kyeongho.domain.valuetype;

import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Embeddable
@Builder
@EqualsAndHashCode
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
	
	public String fullAddress() {
		return getCity() + " " + getStreet() + " " + getZipcode();
	}
	
	public String getCity() {
		return city;
	}
	private void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	private void setStreet(String street) {
		this.street = street;
	}
	public String getZipcode() {
		return zipcode;
	}
	private void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
}
