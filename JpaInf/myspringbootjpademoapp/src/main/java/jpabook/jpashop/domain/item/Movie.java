package jpabook.jpashop.domain.item;

import lombok.Setter;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "M")
@Getter
@Setter
public class Movie extends Item {
	
	private String director;
	private String actor;
	
}
