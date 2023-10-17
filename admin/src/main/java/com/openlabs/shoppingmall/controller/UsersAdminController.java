package com.openlabs.shoppingmall.controller;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.dto.ResponseDto;
import com.openlabs.framework.exception.ShopException;
import com.openlabs.shoppingmall.dto.UserCouponsReqDto;
import com.openlabs.shoppingmall.dto.UserCouponsResDto;
import com.openlabs.shoppingmall.dto.UsersReqDto;
import com.openlabs.shoppingmall.dto.UsersResDto;
import com.openlabs.shoppingmall.service.UsersAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/labshop/v1/admin")
@Api(value = "쿠폰관련 API")
public class UsersAdminController {
    @Autowired
    UsersAdminService service;

    /** 고객관리
     *
     * @param reqDto
     * @return  UserResDto
     * */
    @PutMapping("/u-users")
    @ApiOperation(value = "고객관리")
    public ResponseDto<UsersResDto> updateUser(UsersReqDto reqDto) {
        try {
            return ResponseDto.ok(service.updateUser(reqDto));
        } catch (ShopException e) {
            return ResponseDto.error(401, "고객관리에 실패했습니다.", null, null);
        }
    }
    /** 쿠폰증정
     *
     * @param reqDto
     * @return  UserCouponsResDto
     * */
    @PostMapping("/c-users/coupon")
    @ApiOperation(value = "쿠폰증정")
    public ResponseDto<UserCouponsResDto> giftCoupon(@RequestBody UserCouponsReqDto reqDto) {
        try {
             return ResponseDto.ok(service.giftCoupon(reqDto));
        } catch (ShopException e) {
            return ResponseDto.error(401, "고객관리에 실패했습니다.", null, null);
        }
    }
    /** 고객목록조회
     *
     * @param reqDto
     * @return  UserResDto
     * */
    @GetMapping("/r-users")
    @ApiOperation(value = "고객목록조회")
    public ResponseDto<Slice<UsersResDto>> multiQueryUser(UsersReqDto reqDto, PageDto pageDto) {
        try {
            return ResponseDto.ok(service.multiQueryUser(reqDto, pageDto));
        } catch (ShopException e) {
            return ResponseDto.error(401, "고객목록조회에 실패했습니다.", null, null);
        }
    }
    /** 고객상세조회
     *
     * @param reqDto
     * @return  UserResDto
     * */
    @GetMapping("/r-users/detail")
    @ApiOperation(value = "고객상세조회")
    public ResponseDto<UsersResDto> singleQueryUser(UsersReqDto reqDto) {
        try {
            return ResponseDto.ok(service.singleQueryUser(reqDto));
        } catch (ShopException e) {
            return ResponseDto.error(401, "고객상세조회에 실패했습니다.", null, null);
        }
    }
}
