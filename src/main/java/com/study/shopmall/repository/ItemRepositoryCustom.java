package com.study.shopmall.repository;

import java.util.List;

import com.study.shopmall.entity.Item;

public interface ItemRepositoryCustom {
	List<Item> searchItemSellStatusOrItemDetail();
}
