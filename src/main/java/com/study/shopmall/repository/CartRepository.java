package com.study.shopmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.shopmall.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
