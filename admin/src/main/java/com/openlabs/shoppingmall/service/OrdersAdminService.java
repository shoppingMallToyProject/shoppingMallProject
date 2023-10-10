package com.openlabs.shoppingmall.service;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.exception.ShopException;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.CouponResDto;
import com.openlabs.shoppingmall.dto.OrdersReqDto;
import com.openlabs.shoppingmall.dto.OrdersResDto;
import com.openlabs.shoppingmall.entity.Orders;
import com.openlabs.shoppingmall.entity.Users;
import com.openlabs.shoppingmall.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class OrdersAdminService {
    @Autowired
    OrderRepository orderRepo;
    /** 주문관리 */
    public OrdersResDto updateOrder(OrdersReqDto reqDto) {
        Orders entity = orderRepo.findById(reqDto.getOrderId()).orElseThrow(() -> new ShopException("데이터가 없습니다."));
        // 주문 취소 및 환불 로직
        entity.getUsers().addOrders(entity);
        Orders result = entity.cancel();
        orderRepo.save(result);
        OrdersResDto dto = ObjectConverter.toObject(entity, OrdersResDto.class);
        log.info("dto : ", dto.getUsers().getUserId());

        return dto;
    }
    /** 주문목록조회 */
    public Slice<OrdersResDto> multiQueryOrder(OrdersReqDto reqDto, PageDto pageDto) {
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getSize());
        // 주문 목록조회(order, user)
        Slice<Orders> orderSlice = orderRepo.findSliceBy(pageable);

//        orderRepo.findSliceBy(pageable).map(orders -> ObjectConverter.toObject(orders, OrdersResDto.class));
//        return orderSlice.map(orders -> ObjectConverter.toObject(orders, OrdersResDto.class));
        return null;
    }
    /** 주문상세조회 */
    public OrdersResDto singleQueryOrder(OrdersReqDto reqDto) {
        // 주문 상세조회(order, orderItem, item, userCoupon, User)
        return null;
    }
}