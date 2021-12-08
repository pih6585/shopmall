package com.study.shopmall.mapper;

import org.modelmapper.ModelMapper;


public class DtoMapper {

	private static final ModelMapper modelMapper = new ModelMapper();

	public static ModelMapper getModelMapper() {
		return modelMapper;
	}

}
