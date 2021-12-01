package com.study.shopmall.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.study.shopmall.constant.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	@Setter
	private Member member;

	private LocalDateTime orderDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderItem> ordersItem = new ArrayList<>();

	private LocalDateTime createTime;

	private LocalDateTime updateTime;

	protected Order() {

	}

	private Order(Member member, LocalDateTime orderDate, OrderStatus orderStatus, LocalDateTime createTime,
		LocalDateTime updateTime) {
		this.member = member;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public static Order createOrder(Member member, LocalDateTime orderDate, OrderStatus orderStatus,
		LocalDateTime createTime,
		LocalDateTime updateTime) {
		return new Order(member, orderDate, orderStatus, createTime, updateTime);
	}

}
