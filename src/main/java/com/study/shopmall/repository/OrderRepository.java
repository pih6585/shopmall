package com.study.shopmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.shopmall.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
