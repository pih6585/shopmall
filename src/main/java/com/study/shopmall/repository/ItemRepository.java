package com.study.shopmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.shopmall.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
