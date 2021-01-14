package jpabook.jpashop.domain.member;

import javax.persistence.Embedded;

import jpabook.jpashop.domain.valtype.Address;
import lombok.Builder;
import lombok.Data;

@Data 
@Builder
public class MemberDTO {

	private Long id;

	private String username;

	@Embedded
	private Address address;
}
