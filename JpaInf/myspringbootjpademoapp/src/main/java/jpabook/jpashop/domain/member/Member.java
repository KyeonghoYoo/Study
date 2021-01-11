package jpabook.jpashop.domain.member;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.valtype.Address;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long id;

	private String username;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();
}
