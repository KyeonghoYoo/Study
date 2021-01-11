package jpabook.jpashop.domain.valtype;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
@Getter
public class Address {

	private String city;

	private String street;

	private String zipcode;

	// 하이버네이트가 리플렉션과 같은 기술로 객체를 생성하기 때문에 기본 생성자가 존재해줘야 한다.
	public Address() {
	}

	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}

}
