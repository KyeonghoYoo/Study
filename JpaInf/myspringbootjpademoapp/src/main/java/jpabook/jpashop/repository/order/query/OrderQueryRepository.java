package jpabook.jpashop.repository.order.query;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

	private final EntityManager em;

	public List<OrderQueryDto> findOrderQueryDtos() {
		// orders를 ToOne 연관관계의 엔티티들과 join하여 DTO로 바인딩한다.
		List<OrderQueryDto> result = findOrders();

		// 1:n 관계인 OrderItem의 컬렉션에 대해서는 반복문을 사용해서 조회하여 주입한다.
		result.forEach(o -> {
			List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
			o.setOrderItemQueryDtos(orderItems);
		});

		return result;
	}

	private List<OrderItemQueryDto> findOrderItems(Long orderId) {
		return em.createQuery(
				"select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count) "
						+ "from OrderItem oi " + "join oi.item i " + "where oi.order.id = :orderId",
				OrderItemQueryDto.class).setParameter("orderId", orderId).getResultList();

	}

	public List<OrderQueryDto> findOrders() {
		List<OrderQueryDto> resultList = em.createQuery(
				"select new jpabook.jpashop.repository.order.query.OrderQueryDto(o.id, m.username, o.orderDate, o.status, d.address)"
						+ " from Order o" + " join o.member m" + " join o.delivery d",
				OrderQueryDto.class).getResultList();
		return resultList;
	}

	public List<OrderQueryDto> findAllByDto_optimization() {
		List<OrderQueryDto> result = findOrders();

		// 어플리케이션 단에서 OrderId를 collection으로 모아 In Query를 실행하여 orderItem을 한꺼번에 조회
		// Map을 사용해서 매칭 성능을 O(1)로 최적화
		Map<Long, List<OrderItemQueryDto>> orderItemMap = findOrderItemMap(findOrderIds(result));

		result.forEach(o -> o.setOrderItemQueryDtos(orderItemMap.get(o.getOrderId())));

		return result;
	}

	private Map<Long, List<OrderItemQueryDto>> findOrderItemMap(List<Long> orderIds) {
		List<OrderItemQueryDto> orderItems = em.createQuery(
				"select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count) "
						+ "from OrderItem oi " + "join oi.item i " + "where oi.order.id in :orderIds",
				OrderItemQueryDto.class).setParameter("orderIds", orderIds).getResultList();

		Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems.stream()
				.collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
		return orderItemMap;
	}

	private List<Long> findOrderIds(List<OrderQueryDto> result) {
		List<Long> orderIds = result.stream().map(OrderQueryDto::getOrderId).collect(Collectors.toList());
		return orderIds;
	}

	public List<OrderFlatDto> findAllByDto_flat() {
		return em.createQuery(
				"select new jpabook.jpashop.repository.order.query.OrderFlatDto(o.id, m.username, o.orderDate, o.status, d.address, i.name, oi.orderPrice, oi.count)"
						+ "from Order o " 
						+ "join o.member m " 
						+ "join o.delivery d "
						+ "join o.orderItems oi "
						+ "join oi.item i",
				OrderFlatDto.class).getResultList();
	}

	public List<OrderQueryDto> findAllByDto_flat2() {
		List<OrderFlatDto> flats = em.createQuery(
				"select new jpabook.jpashop.repository.order.query.OrderFlatDto(o.id, m.username, o.orderDate, o.status, d.address, i.name, oi.orderPrice, oi.count)"
						+ "from Order o " 
						+ "join o.member m " 
						+ "join o.delivery d " 
						+ "join o.orderItems oi "
						+ "join oi.item i",
				OrderFlatDto.class).getResultList();
		
		Map<OrderQueryDto, List<OrderItemQueryDto>> map = flats.stream()
				.collect(
						groupingBy(
								o -> new OrderQueryDto(o.getOrderId(), o.getName(), o.getOrderDate(), o.getOrderStatus(), o.getAddress()),
								mapping(o -> new OrderItemQueryDto(o.getOrderId(), o.getItemName(), o.getOrderPrice(), o.getCount()), toList())
								)
						);
		
		map.entrySet().stream().forEach(e -> System.out.println(e.getKey()));
		
		List<OrderQueryDto> collect = map.entrySet().stream()
				.map(e -> new OrderQueryDto(e.getKey().getOrderId(), e.getKey().getName(),
						e.getKey().getOrderDate(), e.getKey().getOrderStatus(), e.getKey().getAddress(),
						e.getValue()))
				.collect(toList());
		System.out.println(collect.size());
		for (OrderQueryDto orderQueryDto : collect) {
			System.out.println("id = " + orderQueryDto.getOrderId());
		}
		return collect;
	}

}
