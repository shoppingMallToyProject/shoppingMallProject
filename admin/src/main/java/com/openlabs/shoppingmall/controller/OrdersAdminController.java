package com.openlabs.shoppingmall.controller;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.dto.ResponseDto;
import com.openlabs.framework.exception.ShopException;
import com.openlabs.shoppingmall.dto.OrdersReqDto;
import com.openlabs.shoppingmall.dto.OrdersResDto;
import com.openlabs.shoppingmall.service.OrdersAdminService;
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
@Api(value = "주문관련 API")
public class OrdersAdminController {
    @Autowired
    OrdersAdminService service;

    /** 주문관리
     *
     * @param reqDto
     * @return  OrdersResDto
     * */
    @PutMapping("/u-order")
    @ApiOperation(value = "주문관리")
    public ResponseDto<OrdersResDto> updateOrder(OrdersReqDto reqDto) {
        try {
            return ResponseDto.ok(service.updateOrder(reqDto));
        } catch (ShopException e) {
            return ResponseDto.error(401, "고객관리 수정에 실패했습니다.", null, null);
        }
    }
    /** 주문목록조회
     *
     * @param reqDto
     * @return  Page<OrdersResDto>
     * */
    @GetMapping("/r-order")
    @ApiOperation(value = "주문목록조회")
    public ResponseDto<Page<OrdersResDto>> multiQueryOrder(OrdersReqDto reqDto, PageDto pageDto) {
        try {
            return ResponseDto.ok(service.multiQueryOrder(reqDto, pageDto));
        } catch (ShopException e) {
            return ResponseDto.error(401, "고객목록조회에 실패했습니다.", null, null);
        }
    }
    /** 주문상세조회
     *
     * @param reqDto
     * @return  OrdersResDto
     * */
    @GetMapping("/r-order/detail")
    @ApiOperation(value = "주문상세조회")
    public ResponseDto<OrdersResDto> singleQueryOrder(OrdersReqDto reqDto) {
        try {
            return ResponseDto.ok(service.singleQueryOrder(reqDto));
        } catch (ShopException e) {
            return ResponseDto.error(401, "고객상세조회에 실패했습니다.", null, null);
        }
    }
}
