package com.study.shopmall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.study.shopmall.constant.Role;
import com.study.shopmall.dto.MemberFormDto;

import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "member")
@Getter
@ToString
public class Member  extends BaseEntity{

	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;

	private String password;

	private String address;

	@Enumerated(EnumType.STRING)
	private Role role;

	protected Member() {

	}

	public Member(String name, String email, String address, String password, Role role) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.password = password;
		this.role = role;
	}

	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		String password = passwordEncoder.encode(memberFormDto.getPassword());
		return new Member(memberFormDto.getName(), memberFormDto.getEmail(), memberFormDto.getAddress(), password,
			Role.ADMIN);
	}
}
