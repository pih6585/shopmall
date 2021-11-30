package com.study.shopmall.service;

import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.shopmall.entity.Member;
import com.study.shopmall.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

	private static final String MEMBER_EXIST_EXCEPTION_MESSAGE = "이미 가입된 회원 입니다.";
	private final MemberRepository memberRepository;

	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member);
	}

	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		if (findMember != null) {
			throw new IllegalStateException(MEMBER_EXIST_EXCEPTION_MESSAGE);
		}

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email);

		if (member == null) {
			throw new UsernameNotFoundException(email);
		}
		return User.builder()
			.username(member.getEmail())
			.password(member.getPassword())
			.roles(member.getRole().toString())
			.build();
	}
}
