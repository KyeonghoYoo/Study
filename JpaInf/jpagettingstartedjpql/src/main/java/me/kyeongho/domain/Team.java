package me.kyeongho.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "team")
@Data @Builder
public class Team {

	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<>();
	
}
