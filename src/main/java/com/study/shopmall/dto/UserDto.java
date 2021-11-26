package com.study.shopmall.dto;

import lombok.Getter;

@Getter
public class UserDto {
	private final String name;
	private final Integer age;

	public UserDto(int age, String name) {
		this.age = age;
		this.name = name;
	}
}

