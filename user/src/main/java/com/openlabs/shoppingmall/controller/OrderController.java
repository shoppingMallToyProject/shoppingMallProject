package com.openlabs.shoppingmall.controller;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.dto.ResponseDto;
import com.openlabs.shoppingmall.dto.CartDto;
import com.openlabs.shoppingmall.dto.CouponDto;
import com.openlabs.shoppingmall.dto.OrderDetailDto;
import com.openlabs.shoppingmall.dto.OrderDto;
import com.openlabs.shoppingmall.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/labshop/v1/order")
@RequiredArgsConstructor
@Slf4j
@ApiOperation(value = "주문 관련 API List")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/c-order")
    @ApiOperation(value = "주문하기")
    public ResponseDto<Boolean> order(@RequestBody List<CartDto> cartList, @RequestBody(required = false) Long couponId, @RequestParam String userId) {
        return ResponseDto.ok(orderService.order(cartList, userId, couponId));
    }

    @PutMapping("/u-order")
    @ApiOperation(value = "주문 취소하기")
    public ResponseDto<Boolean> cancelOrder(long orderId) {
        return ResponseDto.ok(orderService.cancelOrder(orderId));
    }

    @GetMapping("/r-order")
    @ApiOperation(value = "주문 목록 조회")
    public ResponseDto<Slice<OrderDto>> getOrderList(String userId, PageDto pageDto) {
        return ResponseDto.ok(orderService.getOrderList(userId, pageDto));
    }

    @GetMapping("/r-orderDetail")
    @ApiOperation(value = "주문상세정보 조회")
    public ResponseDto<List<OrderDetailDto>> getOrderDetail(long orderId) {
        return ResponseDto.ok(orderService.getOrderDetail(orderId));
    }
}
