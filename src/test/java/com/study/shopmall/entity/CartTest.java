package com.study.shopmall.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.study.shopmall.dto.MemberFormDto;
import com.study.shopmall.entity.Cart;
import com.study.shopmall.entity.Member;
import com.study.shopmall.repository.CartRepository;
import com.study.shopmall.repository.MemberRepository;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class CartTest {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PersistenceContext
	EntityManager em;

	public Member createMember() {
		MemberFormDto memberFormDto = new MemberFormDto();
		memberFormDto.setEmail("test@email.com");
		memberFormDto.setName("홍길동");
		memberFormDto.setAddress("서울 마포");
		memberFormDto.setPassword("1234");
		return Member.createMember(memberFormDto, passwordEncoder);
	}

	@Test
	@DisplayName("장바구니 회원 엔티티 매핑 조회하기")
	public void findCartAndMemberTest() {
		Member member = createMember();
		memberRepository.save(member);

		Cart cart = Cart.createCart(member);
		cartRepository.save(cart);

		em.flush();
		em.clear();

		Cart saveCart = cartRepository.findById(cart.getId())
			.orElseThrow(EntityNotFoundException::new);
		Assertions.assertEquals(saveCart.getMember().getId(), member.getId());
	}

}