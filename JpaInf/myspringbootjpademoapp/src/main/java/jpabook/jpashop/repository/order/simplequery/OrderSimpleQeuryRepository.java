package jpabook.jpashop.repository.order.simplequery;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQeuryRepository {

	private final EntityManager em;

	public List<SimpleOrderQueryDto> findOrderDtos() {
		List<SimpleOrderQueryDto> resultList = em.createQuery("select new jpabook.jpashop.repository.order.simplequery.SimpleOrderQueryDto(o.id, m.username, o.orderDate, o.status, d.address)" + 
														" from Order o" + 
														" join o.member m" + 
														" join o.delivery d", SimpleOrderQueryDto.class)
													.getResultList();
		return resultList;
	}
}
