package com.openlabs.shoppingmall.controller;

import com.openlabs.framework.dto.ResponseDto;
import com.openlabs.shoppingmall.dto.CartDto;
import com.openlabs.shoppingmall.dto.ItemDto;
import com.openlabs.shoppingmall.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"장바구니 관련 API LIST"})
@RestController
@RequestMapping("/labshop/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;

    @GetMapping("/r-cart")
    @ApiOperation(value = "장바구니 조회")
    public ResponseDto<Slice<CartDto>> getCart(@RequestParam String userId) {
        return ResponseDto.ok(cartService.getCart(userId));
    }

    @PostMapping("/c-cart")
    @ApiOperation(value = "장바구니 담기")
    public ResponseDto<Boolean> createCart(@RequestBody CartDto cartDto) {
        return ResponseDto.ok(cartService.createCart(cartDto));
    }


    @PutMapping("/u-cart")
    @ApiOperation(value = "장바구니 수정")
    public ResponseDto<Boolean> updateCart() {
        return null;
    }

    @DeleteMapping("/d-cart")
    @ApiOperation(value = "장바구니 삭제")
    public ResponseDto<Boolean> deleteCart() {
        return null;
    }

}
