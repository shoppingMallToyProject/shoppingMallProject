package com.openlabs.shoppingmall.service;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.exception.ShopException;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.ItemsReqDto;
import com.openlabs.shoppingmall.dto.ItemsResDto;
import com.openlabs.shoppingmall.entity.Items;
import com.openlabs.shoppingmall.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Service
@Validated
public class ItemsAdminService {
    @Autowired
    ItemRepository itemRepo;

    /** 상품등록 */
    public ItemsResDto createItems(@Valid ItemsReqDto reqDto) {
        Optional<Items> entity = itemRepo.findByItemName(reqDto.getItemName());
            if (entity.isPresent() && entity.get().getItemName().equals(reqDto.getItemName())) {
                log.info("같은 이름의 상품이 있습니다. => itemName : {}", entity.get().getItemName());
                throw new ShopException("같은 이름의 상품이 있습니다.");
            }
        return ObjectConverter.toObject(itemRepo.save(reqDto.toEntity()), ItemsResDto.class);
    }
    /** 상품수정 */
    public ItemsResDto updateItems(@Valid ItemsReqDto reqDto) {
        ItemsResDto existedDto = ObjectConverter.toObject(itemRepo.findById(reqDto.getItemId()), ItemsResDto.class);
        if(ObjectUtils.isEmpty(existedDto)) throw new ShopException("데이터가 없습니다.");
        if(existedDto.equals(reqDto))   throw new ShopException("데이터를 변경해 주세요");

        return ObjectConverter.toObject(itemRepo.save(reqDto.toEntity()), ItemsResDto.class);
    }
    /** 상품삭제 */
    public Long deleteItems(@Valid Long itemId) {
        if (!itemRepo.existsById(itemId)) throw new ShopException("삭제할 상품이 없습니다.");
        try {
            itemRepo.deleteById(itemId);
        } catch (Exception e) {
            log.error("삭제오류 : {}", e);
            new ShopException("삭제 실패");
        }

        return itemId;
    }
    /** 상품목록조회 */
    public Slice<ItemsResDto> multiQueryItems(@Valid ItemsReqDto reqDto, PageDto pageDto) {
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getSize());

        Items entity = reqDto.toEntity();
        // 상품명, 시작, 종료일시 조회
        if (StringUtils.hasText(reqDto.getItemName())) {
            return itemRepo.findSliceByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndItemNameContaining(entity.getEventStartTime(), entity.getEventEndTime(), entity.getItemName(), pageable)
                    .map(items -> ObjectConverter.toObject(items, ItemsResDto.class));
        } // 시작, 종료일시 조회
        else {
            return itemRepo.findSliceBy(pageable)
                    .map(items -> ObjectConverter.toObject(items, ItemsResDto.class));
        }
    }
    /** 상품상세조회 */
    public ItemsResDto singleQueryItem( Long itemId) {
        return ObjectConverter.toObject(itemRepo.findById(itemId), ItemsResDto.class);
    }
}