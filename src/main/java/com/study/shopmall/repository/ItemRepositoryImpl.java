package com.study.shopmall.repository;

import static com.study.shopmall.entity.QItem.*;

import java.util.List;

import javax.persistence.EntityManager;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.shopmall.constant.ItemSellStatus;
import com.study.shopmall.entity.Item;

public class ItemRepositoryImpl implements ItemRepositoryCustom {

	private final EntityManager em;
	private final JPAQueryFactory queryFactory;

	public ItemRepositoryImpl(EntityManager em) {
		this.em = em;
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<Item> searchItemSellStatusOrItemDetail() {
		return queryFactory.selectFrom(item)
			.where(item.itemSellStatus.eq(ItemSellStatus.SELL))
			.where(item.itemDetail.like("%" + "테스트 상품 상세 설명2" + "%"))
			.orderBy(item.price.desc())
			.fetch();
	}
}
