package com.openlabs.shoppingmall.controller;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.dto.ResponseDto;
import com.openlabs.framework.exception.ShopException;
import com.openlabs.shoppingmall.dto.ItemTestResDto;
import com.openlabs.shoppingmall.dto.OrderItemTestResDto;
import com.openlabs.shoppingmall.dto.UserTestResDto;
import com.openlabs.shoppingmall.entity.OrderItem;
import com.openlabs.shoppingmall.entity.Orders;
import com.openlabs.shoppingmall.entity.Users;
import com.openlabs.shoppingmall.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/labshop/v1/test")
@Api(value = "어드민 테스트")
public class TestController {
    @Autowired
    TestService service;

    /**
     * 서비스용 유저조회 서비스
     */
    @GetMapping("/r-user")
    @ApiOperation(value = "유저전체조회 테스트")
    public ResponseDto<List<Users>> searchUsers() {
        try {
            return ResponseDto.ok(service.searchUsers());
        } catch (ShopException e) {
            return ResponseDto.error(401, "실패", null, null);
        }
    }

    /**
     * 서비스용 유저상세 서비스
     */
    @GetMapping("/r-user/detail")
    @ApiOperation(value = "유저단건조회 테스트")
    public ResponseDto<UserTestResDto> searchOneUsers(String userId) {
        try {
            return ResponseDto.ok(service.searchOneUsers(userId));
        } catch (ShopException e) {
            return ResponseDto.error(401, "실패", null, null);
        }
    }

    /**
     * 서비스용 유저생성 서비스
     */
    @PostMapping("/c-user")
    @ApiOperation(value = "유저생성 테스트")
    public ResponseDto<UserTestResDto> createUsers(UserTestResDto user) {
        try {
            return ResponseDto.ok(service.createUsers(user));
        } catch (ShopException e) {
            return ResponseDto.error(401, "실패", null, null);
        }
    }

    @PostMapping("/c-order")
    @ApiOperation(value = "유저생성 테스트")
    public ResponseDto<Boolean> createOrder(String userId, @RequestBody List<Long> itemIdList) {
        try {
            service.createOrder(userId, itemIdList);
            return ResponseDto.ok(true);
        } catch (ShopException e) {
            return ResponseDto.error(401, "실패", null, null);
        }
    }

    /**
     * 서비스용 주문상품 단건조회 서비스
     */
    @GetMapping("/r-orderItem/detail")
    @ApiOperation(value = "주문상품 단건조회 테스트")
    public ResponseDto<OrderItemTestResDto> searchOneOrderItem(Long orderItemId) {
        try {
            return ResponseDto.ok(service.searchOneOrderItem(orderItemId));
        } catch (ShopException e) {
            return ResponseDto.error(401, "실패", null, null);
        }
    }

    /**
     * 서비스용 상품생성 서비스
     */
    @PostMapping("/c-item")
    @ApiOperation(value = "상품생성 테스트")
    public ResponseDto<ItemTestResDto> createItem(ItemTestResDto item) {
        try {
            return ResponseDto.ok(service.createItem(item));
        } catch (ShopException e) {
            return ResponseDto.error(401, "실패", null, null);
        }
    }

    @GetMapping("/r-userOrder")
    public ResponseDto<Slice<Orders>> findUserOrder(String userId, PageDto pageDto) {

        return ResponseDto.ok(service.findUserOrder(userId, pageDto));
    }

    @GetMapping("/r-userOrderDetail")
    public ResponseDto<List<OrderItem>> findUserOrderItem(Long orderId, PageDto pageDto) {
        return ResponseDto.ok(service.findUserOrderItem(orderId));
    }
}
