package com.study.shopmall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "cart")
@Getter
@ToString
public class Cart {

	@Id
	@Column(name = "cart_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	protected Cart() {

	}

	private Cart(Long id, Member member) {
		this.id = id;
		this.member = member;
	}

	private Cart(Member member) {
		this.member = member;
	}

	public static Cart createCart(Member member) {
		return new Cart(member);
	}
}
