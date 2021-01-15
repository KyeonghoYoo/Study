package jpabook.jpashop.domain.member;

import jpabook.jpashop.domain.valtype.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MemberDTO {

	private Long id;

	private String name;

	private Address address;
}
