package com.openlabs.shoppingmall.service;

import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.ItemTestResDto;
import com.openlabs.shoppingmall.dto.OrderItemTestResDto;
import com.openlabs.shoppingmall.dto.UserTestResDto;
import com.openlabs.shoppingmall.entity.*;
import com.openlabs.shoppingmall.repository.ItemRepository;
import com.openlabs.shoppingmall.repository.OrderItemRepository;
import com.openlabs.shoppingmall.repository.OrderRepository;
import com.openlabs.shoppingmall.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class TestService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepo;
    @Autowired
    ItemRepository itemRepo;

    /**
     * 서비스용 유저조회 서비스
     */
    public List<Users> searchUsers() {
        return userRepo.findAll();
    }

    /**
     * 서비스용 유저상세 서비스
     */
    public UserTestResDto searchOneUsers(String userId) {
        return ObjectConverter.toObject(userRepo.findById(userId), UserTestResDto.class);
    }

    /**
     * 서비스용 유저생성 서비스
     */
    public UserTestResDto createUsers(UserTestResDto user) {
        Users entity = user.saveEntity();
        userRepo.save(entity);

        return user;
    }

    /**
     * 서비스용 유저생성 서비스
     */
    public void createOrder(String userId, List<Long> idList) {
        Users users = userRepo.findById(userId).get();
        Orders orders = orderRepository.save(Orders.builder()
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDER)
                .users(users)
                .build());

        idList.forEach(id -> {
            Items items = itemRepo.findById(id).get();
            orderItemRepo.save(OrderItem.builder()
                    .orders(orders)
                    .orderPrice(100000l)
                    .orderNumber(32l)
                    .items(items)
                    .build());
        });
    }

    /**
     * 서비스용 주문상품 단건조회 서비스
     */
    public OrderItemTestResDto searchOneOrderItem(Long orderItemId) {
        return ObjectConverter.toObject(orderItemRepo.findById(orderItemId), OrderItemTestResDto.class);
    }

    /**
     * 서비스용 상품생성 서비스
     */
    public ItemTestResDto createItem(ItemTestResDto item) {
        Items entity = item.saveEntity();
        itemRepo.save(entity);

        return item;
    }

    public List<Orders> findUserOrder(String userId) {
        Users users = userRepo.findById(userId).get();
        return orderRepository.findByUsers(users);
    }
    public List<OrderItem> findUserOrderItem(Long orderId) {
        Orders orders = orderRepository.findById(orderId).get();
        return orderItemRepo.findByOrders(orders);
    }
}
