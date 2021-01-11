package jpabook.jpashop.domain.item;

import lombok.Setter;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "A")
@Getter
@Setter
public class Album extends Item {
	
	private String artist;
	private String etc;
	
}
