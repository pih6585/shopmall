package com.study.shopmall.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.study.shopmall.constant.ItemSellStatus;
import com.study.shopmall.entity.Item;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

	@Autowired
	ItemRepository itemRepository;

	@Test
	@DisplayName("상품을 저장한다.")
	public void createItem() {
		Item item = Item.builder()
			.itemName("테스트 상품")
			.price(10000)
			.itemDetail("테스트 상품 상세 설명")
			.itemSellStatus(ItemSellStatus.SELL)
			.stockNumber(100)
			.createTime(LocalDateTime.now())
			.updateTime(LocalDateTime.now())
			.build();

		Item savedItem = itemRepository.save(item);

		assertThat(savedItem).isEqualTo(item);
	}

	public void createItemList() {
		for (int i = 1; i <= 10; i++) {
			Item item = Item.builder()
				.itemName("테스트 상품" + i)
				.price(10000 + i)
				.itemDetail("테스트 상품 상세 설명" + i)
				.itemSellStatus(ItemSellStatus.SELL)
				.stockNumber(100)
				.createTime(LocalDateTime.now())
				.updateTime(LocalDateTime.now())
				.build();

			itemRepository.save(item);
		}
	}

	@Test
	@DisplayName("상품명을 조회한다.")
	public void searchItemByName() {
		this.createItemList();
		List<Item> items = itemRepository.findByItemName("테스트 상품1");
		for (Item item : items) {
			System.out.println(item.toString());
		}
		assertThat(items.get(0).getItemName()).isEqualTo("테스트 상품1");
	}

	@Test
	@DisplayName("상품명 및 상품상세설명중에 조건을 검색한다.")
	public void searchItemByItemNameOrItemDetail() {
		this.createItemList();
		List<Item> items = itemRepository.findByItemNameOrItemDetail("테스트 상품1", "테스트 상품 살세 설명5");
		for (Item item : items) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("가격보다 낮은 조건을 검색한다.")
	public void searchPriceLessThan() {
		this.createItemList();
		List<Item> items = itemRepository.findByPriceLessThan(10005);
		for (Item item : items) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("가격보다 낮은 조건을 큰순으로 검색한다.")
	public void searchPriceLessThanOrderByPriceDesc() {
		this.createItemList();
		List<Item> items = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
		for (Item item : items) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("@Query를 이용한 상품을 조회한다.")
	public void findByItemDetailTest() {
		this.createItemList();
		List<Item> items = itemRepository.findByItemDetail("테스트 상품 상세 설명2");
		for (Item item : items) {
			System.out.println(item.toString());
		}

		assertThat(items.get(0).getItemName()).isEqualTo("테스트 상품2");
	}
}