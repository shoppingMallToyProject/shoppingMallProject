package com.openlabs.shoppingmall.controller;

import com.openlabs.framework.dto.ResponseDto;
import com.openlabs.framework.exception.ShopException;
import com.openlabs.shoppingmall.dto.CouponReqDto;
import com.openlabs.shoppingmall.dto.CouponResDto;
import com.openlabs.shoppingmall.service.CouponsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/labshop/v1/admin")
@Api(value = "쿠폰관련 API")
public class CouponsController {
    @Autowired
    CouponsService service;

    /** 쿠폰등록
     *
     * @param coupon
     * @return  CouponDto
     * */
    @PostMapping("/c-promo")
    @ApiOperation(value = "쿠폰등록")
    public ResponseDto<CouponResDto> createCoupon(CouponReqDto coupon) {
        try {
            return ResponseDto.ok(service.createCoupon(coupon));
        } catch (ShopException e) {
            return ResponseDto.error(401, "쿠폰등록에 실패했습니다.", null, null);
        }
    }
    /** 쿠폰수정
     *
     * @param coupon
     * @return  CouponDto
     * */
    @PutMapping("/u-promo")
    @ApiOperation(value = "쿠폰수정")
    public ResponseDto<CouponResDto> updateCoupon(CouponReqDto coupon) {
        try {
            return ResponseDto.ok(service.updateCoupon(coupon));
        } catch (ShopException e) {
            return ResponseDto.error(401, "쿠폰수정에 실패했습니다.", null, null);
        }
    }

    /** 쿠폰삭제
     *
     * @param couponId
     * @return  couponId
     * */
    @DeleteMapping("/d-promo")
    @ApiOperation(value = "쿠폰삭제")
    public ResponseDto<Long> deleteCoupon(Long couponId) {
        try {
            service.deleteCoupon(couponId);
            return ResponseDto.ok(couponId);
        } catch (ShopException e) {
            return ResponseDto.error(401, "쿠폰삭제에 실패했습니다.", null, null);
        }
    }

    /** 쿠폰목록조회
     *
     * @param reqDto
     * @return  List<CouponResDto>
     * */
    @GetMapping("/r-promo")
    @ApiOperation(value = "쿠폰목록조회")
    public ResponseDto<List<CouponResDto>> multiQueryCoupon(CouponReqDto reqDto) {
        try {
            return ResponseDto.ok(service.multiQueryCoupon(reqDto));
        } catch (ShopException e) {
            return ResponseDto.error(401, "쿠폰수정에 실패했습니다.", null, null);
        }
    }

    /** 쿠폰상세조회
     *
     * @param couponId
     * @return  couponId
     * */
    @GetMapping("/r-promo/detail")
    @ApiOperation(value = "쿠폰상세조회")
    public ResponseDto<CouponResDto> singleQueryCoupon(Long couponId) {
        try {
            return ResponseDto.ok(service.singleQueryCoupon(couponId));
        } catch (ShopException e) {
            return ResponseDto.error(401, "실패", null, null);
        }
    }
}
