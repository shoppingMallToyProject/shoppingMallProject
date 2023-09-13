package com.openlabs.shoppingmall.controller;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.dto.ResponseDto;
import com.openlabs.shoppingmall.dto.ItemDto;
import com.openlabs.shoppingmall.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"상품 관련 API LIST"})
@RestController
@RequestMapping("/labshop/v1/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService itemService;

    @GetMapping("r-item")
    @ApiOperation(value = "상품 검색 다건 조회")
    public ResponseDto<Slice<ItemDto>> searchItem(@RequestParam String itemName, PageDto page) {
        return ResponseDto.ok(itemService.searchItem(itemName, page));
    }

    @GetMapping("r-itemList")
    @ApiOperation(value = "상품 전체 조회 다건 조회")
    public ResponseDto<Slice<ItemDto>> getItemList(PageDto pageDto) {
        return ResponseDto.ok(itemService.getItemList(pageDto));
    }


}
