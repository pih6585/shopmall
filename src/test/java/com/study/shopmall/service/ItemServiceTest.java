package com.study.shopmall.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.study.shopmall.constant.ItemSellStatus;
import com.study.shopmall.dto.ItemFormDto;
import com.study.shopmall.entity.Item;
import com.study.shopmall.entity.ItemImg;
import com.study.shopmall.repository.ItemImgRepository;
import com.study.shopmall.repository.ItemRepository;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemServiceTest {

	@Autowired
	ItemService itemService;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ItemImgRepository itemImgRepository;

	List<MultipartFile> createMultipartFiles() {
		List<MultipartFile> multipartFileList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			String path = "C:/study/shopmall/shop/item/";
			String imageName = "image" + i + ".jpg";
			MockMultipartFile multipartFile =
				new MockMultipartFile(path, imageName, "image/jpg", new byte[] {1, 2, 3, 4});
			multipartFileList.add(multipartFile);
		}

		return multipartFileList;
	}

	@Test
	@DisplayName("상품 등록 테스트")
	@WithMockUser(username = "admin", roles = "ADMIN")
	void saveItem() throws Exception {
		ItemFormDto itemFormDto = new ItemFormDto();
		itemFormDto.setItemName("테스트상품");
		itemFormDto.setItemSellStatus(ItemSellStatus.SELL);
		itemFormDto.setItemDetail("테스트 상품 입니다.");
		itemFormDto.setPrice(1000);
		itemFormDto.setStockNumber(100);

		List<MultipartFile> multipartFileList = createMultipartFiles();
		Long itemId = itemService.saveItem(itemFormDto, multipartFileList);
		List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);

		Item item = itemRepository.findById(itemId)
			.orElseThrow(EntityNotFoundException::new);

		assertEquals(itemFormDto.getItemName(), item.getItemName());
		assertEquals(itemFormDto.getItemSellStatus(), item.getItemSellStatus());
		assertEquals(itemFormDto.getItemDetail(), item.getItemDetail());
		assertEquals(itemFormDto.getPrice(), item.getPrice());
		assertEquals(itemFormDto.getStockNumber(), item.getStockNumber());
		assertEquals(multipartFileList.get(0).getOriginalFilename(), itemImgList.get(0).getOriImgName());
	}

}