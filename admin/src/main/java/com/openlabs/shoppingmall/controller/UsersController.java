package com.openlabs.shoppingmall.controller;

import com.openlabs.framework.dto.ResponseDto;
import com.openlabs.framework.exception.ShopException;
import com.openlabs.shoppingmall.dto.UsersReqDto;
import com.openlabs.shoppingmall.dto.UsersResDto;
import com.openlabs.shoppingmall.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/labshop/v1/admin")
@Api(value = "쿠폰관련 API")
public class UsersController {
    @Autowired
    UsersService service;

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
            return ResponseDto.error(401, "쿠폰등록에 실패했습니다.", null, null);
        }
    }
    /** 고객관리
     *
     * @param reqDto
     * @return  UserResDto
     * */
    @GetMapping("/r-users")
    @ApiOperation(value = "고객관리")
    public ResponseDto<Page<UsersResDto>> multiQueryUser(UsersReqDto reqDto, Pageable pageable) {
        try {
            return ResponseDto.ok(service.multiQueryUser(reqDto, pageable));
        } catch (ShopException e) {
            return ResponseDto.error(401, "쿠폰등록에 실패했습니다.", null, null);
        }
    }
}
