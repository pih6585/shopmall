package com.study.shopmall.dto;

import org.modelmapper.ModelMapper;

import com.study.shopmall.entity.ItemImg;
import com.study.shopmall.mapper.DtoMapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemImgDto {

	private Long id;

	private String imgName;

	private String oriImgName;

	private String imgUrl;

	private String repImgYn;

	public static ItemImgDto of(ItemImg itemImg) {
		return DtoMapper.getModelMapper()
			.map(itemImg, ItemImgDto.class);
	}

}
