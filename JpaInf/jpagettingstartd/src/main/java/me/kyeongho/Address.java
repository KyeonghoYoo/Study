package me.kyeongho;

import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
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

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getZipcode() {
		return zipcode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCity(), getStreet(), getZipcode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		else {
			Address address = (Address) obj;
			return Objects.equals(getCity(), address.getCity()) 
					&& Objects.equals(getStreet(), address.getStreet())
					&& Objects.equals(getZipcode(), address.getZipcode());
		}
	}

}
