package com.openlabs.shoppingmall.service;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.shoppingmall.dto.OrdersReqDto;
import com.openlabs.shoppingmall.dto.OrdersResDto;
import com.openlabs.shoppingmall.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class OrdersAdminService {
    @Autowired
    OrderRepository orderRepo;
    /** 주문관리 */
    public OrdersResDto updateOrder(OrdersReqDto reqDto) {
        // 주문 취소 및 환불 로직

        return null;
    }
    /** 주문목록조회 */
    public Page<OrdersResDto> multiQueryOrder(OrdersReqDto reqDto, PageDto pageDto) {
        // 주문 목록조회(order, user)
        return null;
    }
    /** 주문상세조회 */
    public OrdersResDto singleQueryOrder(OrdersReqDto reqDto) {
        // 주문 상세조회(order, orderItem, item, userCoupon, User)
        return null;
    }
}