package com.openlabs.shoppingmall.service;

import com.openlabs.framework.exception.ShopException;
import com.openlabs.shoppingmall.AdminApplication;
import com.openlabs.shoppingmall.entity.*;
import com.openlabs.shoppingmall.repository.ItemRepository;
import com.openlabs.shoppingmall.repository.OrderItemRepository;
import com.openlabs.shoppingmall.repository.OrderRepository;
import com.openlabs.shoppingmall.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest(classes = AdminApplication.class)
class OrdersAdminServiceTest {
    @Autowired
    UserRepository userRepo;
    @Autowired
    ItemRepository itemRepo;
    @Autowired
    OrderRepository orderRepo;
    @Autowired
    OrderItemRepository orderItemRepo;

    @Test
    @Rollback
    void RegistTest(){
        // 주소 등록
        Address address = Address.builder()
                .city("서울시")
                .street("테헤란로")
                .zipcode("12345")
                .build();
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);

        // 유저 등록
        Users user = Users.builder()
                .userId("testUser")
                .userPw("1234")
                .userName("테스터")
                .userRating(UserRating.BRONZE)
                .userStatus(UserStatus.NORMAL)
                .addresses(addresses)
                .build();
        Address tmp = Address.builder()
                .city("서울시")
                .street("테헤란로")
                .zipcode("12345")
                .users(user)
                .build();

        user.getAddresses().add(tmp);

//        userRepo.save(user);
    }

    @Test
    @Rollback
    void OrderTest(){
        // 유저조회
        Optional<Users> userEntity = userRepo.findById("testUser");
        // 상품조회
        Optional<Items> itemEntity = itemRepo.findById(3L);
        // 주문 생성

        Orders order = Orders.builder().build().orderCreate(userEntity.get());
        // 상품주문 생성
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(OrderItem.builder().build().createOrderItem(itemEntity.get(), order, 2));
        // 주문 총액 추가
        order.setTotalPrice(order.getTotalPrice(orderItems));

        System.out.println("order.getTotalPrice() = " + order.getTotalPrice());
        orderItems.stream().forEach(orderItem -> System.out.println("orderItem.getTotalPrice() = " + orderItem.getTotalPrice(itemEntity.get(), orderItem.getOrderQuantity())));

        orderRepo.save(order);
        orderItems.stream().forEach(orderItem -> orderItemRepo.save(orderItem));
    }

    @Test
    @Rollback
    void updateOrderTest() throws Exception{
        // given
        Long orderId = 1l;
        // when
        Orders entity = orderRepo.findById(orderId).orElseThrow(() -> new ShopException("데이터가 없습니다."));
        entity.getUsers().addOrders(entity);
//        Users user = userRepo.findById(entity.getUsers().getUserId()).get();
        Users user = entity.getUsers();
        System.out.println("user = " + user.toString());
        System.out.println("user.getUserId() = " + user.getUserId());
        Orders result = entity.cancel();
        System.out.println("result.getUsers() = " + result.getUsers().getUserId());
        System.out.println("result.getOrderStatus() = " + result.getOrderStatus());
        // then
        orderRepo.save(result);
//        orderRepo.findAll();
    }
}