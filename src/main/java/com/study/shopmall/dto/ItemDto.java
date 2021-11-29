package com.study.shopmall.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemDto {


	private Long id;

	private String itemName;

	private Integer price;

	private String itemDetail;

	private String sellStatCd;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;

	@Builder
	public ItemDto(Long id, String itemName, Integer price, String itemDetail, String sellStatCd,
		LocalDateTime createTime, LocalDateTime updateTime) {
		this.id = id;
		this.itemName = itemName;
		this.price = price;
		this.itemDetail = itemDetail;
		this.sellStatCd = sellStatCd;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
}
