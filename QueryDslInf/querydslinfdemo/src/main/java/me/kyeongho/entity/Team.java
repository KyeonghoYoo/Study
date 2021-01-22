package me.kyeongho.entity;

import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id", "name" })
public class Team {
	
	@Id @GeneratedValue
	@Column(name = "team_id")
	private Long id;
	
	String name;
	
	@OneToMany(mappedBy = "team")
	List<Member> members = new ArrayList<Member>();

	public Team(String name) {
		super();
		this.name = name;
	}

}
