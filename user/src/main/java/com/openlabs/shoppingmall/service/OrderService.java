package com.openlabs.shoppingmall.service;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.CartDto;
import com.openlabs.shoppingmall.dto.OrderDetailDto;
import com.openlabs.shoppingmall.dto.OrderDto;
import com.openlabs.shoppingmall.entity.*;
import com.openlabs.shoppingmall.repository.OrderItemRepository;
import com.openlabs.shoppingmall.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public boolean order(List<CartDto> cartList, String userId) {
        Users users = userService.getUser(userId);
        Orders orders = saveOrder(users);
        saveOrderItem(orders, cartList);
        return true;
    }

    public Orders saveOrder(Users users) {
        Orders orders = Orders.builder()
                .orderDate(LocalDateTime.now())
                .users(users)
                .orderStatus(OrderStatus.ORDER)
                .build();
        return orderRepository.save(orders);
    }

    public boolean cancelOrder(long orderId) {
        Orders orders = getOrders(orderId);
        orders.setOrderStatus(OrderStatus.CANCEL);
        orderRepository.save(orders);
        return true;
    }

    public void saveOrderItem(Orders orders, List<CartDto> cartList) {
        cartList.forEach(cartDto -> {
            Items items = itemService.findItem(cartDto.getItemId());
            orderItemRepository.save(OrderItem.builder()
                    .items(items)
                    .orderQuantity(cartDto.getItemNumber())
                    .orders(orders)
                    .totalPrice(items.getItemPrice() * cartDto.getItemNumber())
                    .build());
        });
    }

    public Slice<OrderDto> getOrderList(String userId, PageDto pageDto) {
        Users users = userService.getUser(userId);
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getSize());
        return orderRepository.findByUsers(users, pageable)
                .map(order -> ObjectConverter.toObject(order, OrderDto.class));
    }

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
