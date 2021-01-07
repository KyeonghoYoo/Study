package me.kyeongho.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "member")
@Data @Builder
public class Member {

	@Id @GeneratedValue
	private Long id;

	private String name;

	private int age;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;

	@OneToMany(mappedBy = "member")
	List<Order> orders = new ArrayList<>();
	
	public Member() {
	}

	public Member(Long id, String name, int age, Team team, List<Order> orders) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.team = team;
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	

}
