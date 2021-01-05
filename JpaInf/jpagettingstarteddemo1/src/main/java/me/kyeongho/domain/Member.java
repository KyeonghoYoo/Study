package me.kyeongho.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.kyeongho.domain.valuetype.Address;
import me.kyeongho.domain.valuetype.Period;

@Entity
@Table(name = "member")
@Getter @Setter @ToString
public class Member extends BaseEntity {
	
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	private String name;
	
	// 기간
	@Embedded
	private Period period;
	
	// 주소
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Order> order = new ArrayList<>();
}
