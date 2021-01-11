package jpabook.jpashop.domain.item;

import lombok.Setter;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "B")
@Getter
@Setter
public class Book extends Item {
	
	private String author;
	private String isbn;
	
}
