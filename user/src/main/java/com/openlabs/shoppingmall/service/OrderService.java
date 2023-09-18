package com.openlabs.shoppingmall.service;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.CartDto;
import com.openlabs.shoppingmall.dto.CouponDto;
import com.openlabs.shoppingmall.dto.OrderDetailDto;
import com.openlabs.shoppingmall.dto.OrderDto;
import com.openlabs.shoppingmall.entity.*;
import com.openlabs.shoppingmall.repository.OrderItemRepository;
import com.openlabs.shoppingmall.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;
    private final ItemService itemService;
    private final CouponService couponService;

    /**
     * 사용자 주문 API
     */
    public boolean order(List<CartDto> cartList, String userId, long couponId) {
        Users users = userService.getUser(userId);
        Coupons coupons = couponService.getCoupon(couponId);
        //쿠폰 사용 처리 메소드가 필요함
        Orders orders = Orders.orderCreate(users);
        saveOrderItem(orders, cartList);
        return true;
    }

    /**
     * 사용자 주문 취소 API
     */
    public boolean cancelOrder(long orderId) {
        Orders orders = getOrders(orderId);
        Orders.cancel(orders);
        orderRepository.save(orders);
        return true;
    }

    /**
     * 사용자 주문 내역 orderItem 저장 로직
     */
    public void saveOrderItem(Orders orders, List<CartDto> cartList) {
        cartList.forEach(cartDto -> {
            Items items = itemService.findItem(cartDto.getItemId());
            orderItemRepository.save(OrderItem.createOrderItem(items, orders, cartDto.getItemNumber()));
        });
    }

    /**
     * 주문 목록 조회 API Orders 테이블만 조회
     */
    public Slice<OrderDto> getOrderList(String userId, PageDto pageDto) {
        Users users = userService.getUser(userId);
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getSize());
        return orderRepository.findByUsers(users, pageable)
                .map(order -> ObjectConverter.toObject(order, OrderDto.class));
    }

    /**
     * 주문 상세 정보 조회 API Orders와 OrderItem 테이블 같이 조회
     */
    public List<OrderDetailDto> getOrderDetail(long orderId) {
        Orders orders = getOrders(orderId);
        List<OrderItem> orderItemList = getOrderItemList(orders);

        List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
        orderItemList.forEach(orderItem -> {
            orderDetailDtoList.add(OrderDetailDto.builder()
                    .orderId(orderItem.getOrders().getOrderId())
                    .orderStatus(orderItem.getOrders().getOrderStatus())
                    .itemId(orderItem.getItems().getItemId())
                    .itemNumber(orderItem.getOrderQuantity())
                    .build());
        });
        return orderDetailDtoList;
    }

    public Orders getOrders(long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<OrderItem> getOrderItemList(Orders orders) {
        return orderItemRepository.findByOrders(orders);
    }
}
