package com.openlabs.shoppingmall.service;

import com.openlabs.framework.exception.ShopException;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.ItemsReqDto;
import com.openlabs.shoppingmall.dto.ItemsResDto;
import com.openlabs.shoppingmall.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Slf4j
@Service
@Validated
public class ItemsService {
    @Autowired
    ItemRepository itemRepo;

    /** 상품등록 */
    public ItemsResDto createItems(@Valid ItemsReqDto reqDto) {
        return ObjectConverter.toObject(itemRepo.save(reqDto.toEntity()), ItemsResDto.class);
    }
    /** 상품수정 */
    public ItemsResDto updateItems(@Valid ItemsReqDto reqDto) {
        return ObjectConverter.toObject(itemRepo.save(reqDto.toEntity()), ItemsResDto.class);
    }
    /** 상품삭제 */
    public Long deleteItems(@Valid Long itemId) {
        try {
            itemRepo.deleteById(itemId);
        } catch (Exception e) {
            log.error("삭제오류 : {}", e);
            new ShopException("삭제 실패");
        }

        return itemId;
    }
    /** 상품목록조회 */
    public Slice<ItemsResDto> multiQueryItems(ItemsReqDto reqDto
            , int page
            ,  int size
//            , PageDto pageDto
    ) {
        Pageable pageable = PageRequest.of(page, size);

//        Pageable pageable = PageRequest.of(reqDto.getPageNumber(), reqDto.getSize());
        return itemRepo.findSliceBy(pageable)
                .map(items -> ObjectConverter.toObject(items, ItemsResDto.class));
    }
    /** 상품상세조회 */
    public ItemsResDto singleQueryItem(@Valid Long itemId) {
        return ObjectConverter.toObject(itemRepo.findById(itemId), ItemsResDto.class);
    }
}