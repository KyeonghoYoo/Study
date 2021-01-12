package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;

	/**
	 * 저장
	 * @param item
	 */
	public void save(Item item) {
		if (item.getId() == null) {
			em.persist(item);
		} else {
			em.merge(item);
		}
	}

	/**
	 * 단건 조회
	 * @param id
	 * @return
	 */
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	/**
	 * 전체 조회
	 * @return
	 */
	public List<Item> findAll(){
		return em.createQuery("select i from Item i", Item.class)
				.getResultList();
	}
}
