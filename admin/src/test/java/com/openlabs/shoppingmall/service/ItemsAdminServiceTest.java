package com.openlabs.shoppingmall.service;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.AdminApplication;
import com.openlabs.shoppingmall.dto.ItemsResDto;
import com.openlabs.shoppingmall.entity.Items;
import com.openlabs.shoppingmall.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.Optional;

@Slf4j
@SpringBootTest(classes = AdminApplication.class)
class ItemsAdminServiceTest {
    @Autowired
    ItemRepository itemRepo;

    @Test
    void multiQueryItemsTest(){
        // given
        PageDto pageDto = new PageDto(1, 10);

        // when
        PageRequest pageRequest = PageRequest.of(pageDto.getPageNumber(), pageDto.getSize());
        Slice<ItemsResDto> items = itemRepo.findSliceBy(pageRequest).map(items1 -> ObjectConverter.toObject(items1, ItemsResDto.class));

        // then
        System.out.println("items = " + items);
    }

    @Test
    void discountItemPriceTest(){
        // given
        // 할인가 적용 테스트
        Optional<Items> entity = itemRepo.findById(3L);
        System.out.println("entity.itemPrice = " + entity.get().getItemPrice());
        System.out.println("entity.get().discountItemPrice() = " + entity.get().getDiscountedItemPrice());
    }
}