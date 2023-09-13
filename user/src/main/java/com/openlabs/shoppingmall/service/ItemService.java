package com.openlabs.shoppingmall.service;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.ItemDto;
import com.openlabs.shoppingmall.entity.Items;
import com.openlabs.shoppingmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Slice<ItemDto> searchItem(String itemName, PageDto pageDto) {
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getSize());
        return itemRepository.findByItemName(itemName, pageable)
                .map(items -> ObjectConverter.toObject(items, ItemDto.class));
    }

    public Slice<ItemDto> getItemList(PageDto pageDto) {
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getSize());
        return itemRepository.findSliceBy(pageable)
                .map(items -> ObjectConverter.toObject(items, ItemDto.class));
    }

    public Items findItem(long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }
}
