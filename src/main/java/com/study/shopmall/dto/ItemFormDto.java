package com.study.shopmall.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.study.shopmall.constant.ItemSellStatus;
import com.study.shopmall.entity.Item;
import com.study.shopmall.mapper.DtoMapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemFormDto {

	private Long id;

	@NotBlank(message = "상품명은 필수 입력 값입니다.")
	private String itemName;

	@NotNull(message = "가격은 필수 입력 값입니다.")
	private int price;

	@NotBlank(message = "상세정보 필수 입력 값입니다.")
	private String itemDetail;

	@NotNull(message = "재고는 필수 입력 값입니다.")
	private int stockNumber;

	private ItemSellStatus itemSellStatus;

	private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

	private List<Long> itemImgIds = new ArrayList<>();

	public Item createItem() {
		return DtoMapper.getModelMapper()
			.map(this, Item.class);
	}

	public static ItemFormDto of(Item item) {
		return DtoMapper.getModelMapper()
			.map(item, ItemFormDto.class);
	}

}
