package com.study.shopmall.entity;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.study.shopmall.constant.ItemSellStatus;
import com.study.shopmall.repository.ItemRepository;
import com.study.shopmall.repository.MemberRepository;
import com.study.shopmall.repository.OrderItemRepository;
import com.study.shopmall.repository.OrderRepository;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class OrderTest {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	OrderItemRepository orderItemRepository;

	@PersistenceContext
	EntityManager em;

	public Item createItem() {
		return Item.builder()
			.itemName("테스트 상품")
			.price(10000)
			.itemDetail("상세 설명")
			.itemSellStatus(ItemSellStatus.SELL)
			.stockNumber(100)
			.createTime(LocalDateTime.now())
			.updateTime(LocalDateTime.now())
			.build();
	}

	@Test
	@DisplayName("영속성 전이 테스트")
	public void casecadeTest() {
		Order order = new Order();
		for (int i = 0; i < 3; i++) {
			Item item = this.createItem();
			itemRepository.save(item);
			OrderItem orderItem = OrderItem.createOrderItem(item, order, 1000, 10);
			order.getOrdersItem().add(orderItem);
		}
		orderRepository.save(order);
		em.flush();
		em.clear();

		Order saveOrder = orderRepository.findById(order.getId())
			.orElseThrow(EntityNotFoundException::new);

		Assertions.assertEquals(3, saveOrder.getOrdersItem().size());
	}

	@Test
	@DisplayName("고아객체 제거 테스트")
	public void orphanRemovalTest() {
		Order order = this.createOrder();
		order.getOrdersItem().remove(0);
		em.flush();
	}

	private Order createOrder() {
		Order order = new Order();
		for (int i = 0; i < 3; i++) {
			Item item = this.createItem();
			itemRepository.save(item);
			OrderItem orderItem = OrderItem.createOrderItem(item, order, 1000, 10);
			order.getOrdersItem().add(orderItem);
		}
		Member member = new Member();
		memberRepository.save(member);
		order.setMember(member);
		orderRepository.save(order);
		return order;
	}

	@Test
	@DisplayName("지연 로딩 테스트")
	public void lazyLoadingTest() {
		Order order = this.createOrder();
		Long orderItemId = order.getOrdersItem().get(0).getId();
		em.flush();
		em.clear();

		OrderItem orderItem = orderItemRepository.findById(orderItemId)
			.orElseThrow(EntityNotFoundException::new);
		System.out.println("Order class : " + orderItem.getOrder().getClass());

	}

}