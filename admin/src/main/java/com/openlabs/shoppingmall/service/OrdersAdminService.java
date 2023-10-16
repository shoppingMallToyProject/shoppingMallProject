package com.openlabs.shoppingmall.service;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.exception.ShopException;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.OrdersDetailResDto;
import com.openlabs.shoppingmall.dto.OrdersReqDto;
import com.openlabs.shoppingmall.dto.OrdersResDto;
import com.openlabs.shoppingmall.entity.Items;
import com.openlabs.shoppingmall.entity.OrderItem;
import com.openlabs.shoppingmall.entity.Orders;
import com.openlabs.shoppingmall.repository.OrderItemRepository;
import com.openlabs.shoppingmall.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Validated
public class OrdersAdminService {
    @Autowired
    OrderRepository orderRepo;
    @Autowired
    OrderItemRepository orderItemRepo;

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

        return orderSlice.map(orders -> ObjectConverter.toObject(orders, OrdersResDto.class));
    }
    /** 주문상세조회 */
    public OrdersDetailResDto singleQueryOrder(OrdersReqDto reqDto) {
        // 주문 상세조회(order, orderItem, item, userCoupon, User)
        Orders order = orderRepo.findById(reqDto.getOrderId()).orElse(null);
        List<OrderItem> orderItem = orderItemRepo.findByOrders(order);
        List<Items> itemList = new ArrayList<>();

        orderItem.stream().forEach(orderItem1 -> {
            itemList.add(orderItem1.getItems());
        });

        OrdersDetailResDto result = OrdersDetailResDto.builder()
                .users(order.getUsers())
                .orderItem(orderItem)
                .items(itemList)
                .orderId(order.getOrderId())
                .orderStatus(order.getOrderStatus())
                .orderDate(order.getOrderDate())
                .totalPrice(order.getTotalPrice())
                .build();

        return result;
    }
}