package com.study.shopmall.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.study.shopmall.dto.MemberFormDto;
import com.study.shopmall.entity.Member;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {

	@Autowired
	MemberService memberService;

	@Autowired
	PasswordEncoder passwordEncoder;

	public Member createMember() {
		MemberFormDto memberFormDto = new MemberFormDto();
		memberFormDto.setEmail("test@email.com");
		memberFormDto.setName("홍길동");
		memberFormDto.setAddress("서울시 마포구 합저동");
		memberFormDto.setPassword("!234");
		return Member.createMember(memberFormDto, passwordEncoder);
	}

	@Test
	@DisplayName("회원가입 테스트")
	public void saveMemberTest() {
		Member member = createMember();
		Member savedMember = memberService.saveMember(member);

		assertThat(member.getEmail()).isEqualTo(savedMember.getEmail());
		assertThat(member.getName()).isEqualTo(savedMember.getName());
		assertThat(member.getAddress()).isEqualTo(savedMember.getAddress());
		assertThat(member.getPassword()).isEqualTo(savedMember.getPassword());
		assertThat(member.getRole()).isEqualTo(savedMember.getRole());
	}

	@Test
	@DisplayName("중복 회원 가입 테스트")
	public void saveDuplicateMemberTest() {
		Member member1 = createMember();
		Member member2 = createMember();
		memberService.saveMember(member1);

		assertThatThrownBy(() -> {
			memberService.saveMember(member2);
		}).isInstanceOf(IllegalStateException.class)
			.hasMessageContaining("이미 가입된 회원 입니다.");
	}

}