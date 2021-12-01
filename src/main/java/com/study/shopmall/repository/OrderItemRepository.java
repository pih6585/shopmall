package com.study.shopmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.shopmall.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
