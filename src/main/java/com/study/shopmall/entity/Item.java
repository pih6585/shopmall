package com.study.shopmall.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.study.shopmall.constant.ItemSellStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "item")
@Getter
@ToString
public class Item {

	@Id
	@Column(name = "item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 50)
	private String itemName;

	@Column(nullable = false, name = "price")
	private int price;

	@Column(nullable = false)
	private int stockNumber;

	@Lob
	@Column(nullable = false)
	private String itemDetail;

	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;

	protected Item() {

	}

	@Builder
	public Item(Long id, String itemName, int price, int stockNumber, String itemDetail,
		ItemSellStatus itemSellStatus, LocalDateTime createTime, LocalDateTime updateTime) {
		this.id = id;
		this.itemName = itemName;
		this.price = price;
		this.stockNumber = stockNumber;
		this.itemDetail = itemDetail;
		this.itemSellStatus = itemSellStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

}
