package com.study.shopmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.shopmall.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByEmail(String email);
}
