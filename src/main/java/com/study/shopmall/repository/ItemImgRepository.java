package com.study.shopmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.shopmall.entity.ItemImg;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

	List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
}
