package com.openlabs.shoppingmall.controller;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.dto.ResponseDto;
import com.openlabs.shoppingmall.dto.CouponDto;
import com.openlabs.shoppingmall.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"쿠폰 관련 API LIST"})
@RestController
@RequestMapping("/labshop/v1/coupon")
@RequiredArgsConstructor
@Slf4j
public class CouponController {

    private final CouponService couponService;

    @GetMapping("/r-coupon")
    @ApiOperation(value = "사용자 쿠폰 조회")
    public ResponseDto<List<CouponDto>> getCouponList(String userId) {
        return ResponseDto.ok(couponService.getCouponList(userId));
    }
}
