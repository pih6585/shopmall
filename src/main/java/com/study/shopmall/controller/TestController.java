package com.study.shopmall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.shopmall.dto.UserDto;

@RestController
public class TestController {


	@GetMapping(value = "/test")
	public UserDto test(){
		return new UserDto(20,"park");
	}
}
