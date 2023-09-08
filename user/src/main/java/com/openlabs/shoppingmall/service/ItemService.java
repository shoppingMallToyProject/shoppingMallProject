package com.openlabs.shoppingmall.service;

import com.openlabs.framework.dto.ResponseDto;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.ItemDto;
import com.openlabs.shoppingmall.entity.Items;
import com.openlabs.shoppingmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


}
