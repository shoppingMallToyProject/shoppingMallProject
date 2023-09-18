package com.openlabs.shoppingmall.controller;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.dto.ResponseDto;
import com.openlabs.framework.exception.ShopException;
import com.openlabs.shoppingmall.dto.UsersReqDto;
import com.openlabs.shoppingmall.dto.UsersResDto;
import com.openlabs.shoppingmall.service.UsersAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    /** 고객목록조회
     *
     * @param reqDto
     * @return  UserResDto
     * */
    @GetMapping("/r-users")
    @ApiOperation(value = "고객목록조회")
    public ResponseDto<Page<UsersResDto>> multiQueryUser(UsersReqDto reqDto, PageDto pageDto) {
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
