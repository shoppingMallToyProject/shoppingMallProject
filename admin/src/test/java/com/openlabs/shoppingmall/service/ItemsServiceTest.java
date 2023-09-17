package com.openlabs.shoppingmall.service;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.AdminApplication;
import com.openlabs.shoppingmall.dto.ItemsReqDto;
import com.openlabs.shoppingmall.dto.ItemsResDto;
import com.openlabs.shoppingmall.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

@Slf4j
@SpringBootTest(classes = AdminApplication.class)
class ItemsServiceTest {
    @Autowired
    ItemRepository itemRepo;

    @Test
    void multiQueryItems()  throws Exception{
        // given
        ItemsReqDto reqDto = ItemsReqDto.builder()
                .eventStartTime("2023-09-16T07:20:32")
                .eventEndTime("2023-09-20T07:20:32")
                .build();
        PageDto pageDto = new PageDto(1, 10);

        // when
        PageRequest pageRequest = PageRequest.of(pageDto.getPageNumber(), pageDto.getSize());
        Slice<ItemsResDto> items = itemRepo.findSliceBy(pageRequest).map(items1 -> ObjectConverter.toObject(items1, ItemsResDto.class));

        // then
        System.out.println("items = " + items);
    }
}