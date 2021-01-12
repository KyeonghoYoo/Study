package jpabook.jpashop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderStatus;
import jpabook.jpashop.domain.valtype.Address;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.service.OrderService;

@SpringBootTest
@Transactional
public class OrderServiceTest {

	@Autowired EntityManager em;
	@Autowired OrderService orderService;
	@Autowired OrderRepository orderRepository;
	
	@Test
	public void 상품주문() throws Exception {
		//given
		Member member = createMember("회원1", "서울", "송파", "123-123");
		
		Item book = createBook("JPA 실습", 100000, 10);
		
		int orderCount = 2;
		
		//when
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		//then
		Order findOrder = orderRepository.findOne(orderId);
		
		assertEquals(OrderStatus.ORDER, findOrder.getStatus(), "상품 주문시 상태는 ORDER");
		assertEquals(1, findOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
		assertEquals(100000 * orderCount, findOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
		assertEquals(8, book.getStockQuantity(), "주문 수량만큼 재고가 줄어야 한다.");		
	}


	
	@Test
	public void 상품주문_재고수량초과() throws Exception {
		//given
		Member member = createMember("회원1", "서울", "송파", "123-123");
		
		Item book = createBook("JPA 실습", 100000, 10);
		
		int orderCount = 11;
		//when
		NotEnoughStockException e = assertThrows(NotEnoughStockException.class, 
				() -> orderService.order(member.getId(), book.getId(), orderCount), 
				"재고 수량 초과 에러가 발생해야 한다!!!");
		
		//then
		String message = e.getMessage();
		assertEquals("need more stock", message);
	}
	
	@Test
	public void 주문취소() throws Exception {
		//given
		Member member = createMember("회원1", "서울", "송파", "123-123");
		Item book = createBook("JPA 실습", 10000, 10);
		
		int orderCount = 2;
		
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		//when
		orderService.cancelOrder(orderId);
		
		//then
		Order findOrder = orderRepository.findOne(orderId);
		
		assertEquals(OrderStatus.CANCEL, findOrder.getStatus(), "주문 상태는 CANCEL이여야 한다.");
		assertEquals(10, book.getStockQuantity(), "주문이 취소된 상품은 그만큼 재고가 증가해야 한다.");
		
	}
	
	private Item createBook(String name, int price, int quantity) {
		Item book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setStockQuantity(quantity);
		em.persist(book);
		return book;
	}

	private Member createMember(String username, String city, String street, String zipcode) {
		Member member = new Member();
		member.setUsername(username);
		member.setAddress(new Address(city, street, zipcode));
		em.persist(member);
		return member;
	}
}
