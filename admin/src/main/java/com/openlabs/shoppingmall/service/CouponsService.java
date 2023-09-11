package com.openlabs.shoppingmall.service;

import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.CouponReqDto;
import com.openlabs.shoppingmall.dto.CouponResDto;
import com.openlabs.shoppingmall.repository.CouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
public class CouponsService {
    @Autowired
    CouponRepository couponRepo;

//    /**
//     * 서비스용 유저조회 서비스
//     */
//    public List<Users> searchUsers() {
//        return userRepo.findAll();
//    }
//
//    /**
//     * 서비스용 유저상세 서비스
//     */
//    public UserTestResDto searchOneUsers(String userId) {
//        return ObjectConverter.toObject(userRepo.findById(userId), UserTestResDto.class);
//    }
//
//    /**
//     * 서비스용 유저생성 서비스
//     */
//    public UserTestResDto createUsers(UserTestResDto user) {
//        Users entity = user.saveEntity();
//        userRepo.save(entity);
//
//        return user;
//    }
//
//    /**
//     * 서비스용 유저생성 서비스
//     */
//    public void createOrder(String userId, List<Long> idList) {
//        Users users = userRepo.findById(userId).get();
//        Orders orders = orderRepository.save(Orders.builder()
//                .orderDate(LocalDateTime.now())
//                .orderStatus(OrderStatus.ORDER)
//                .users(users)
//                .build());
//
//        idList.forEach(id -> {
//
//            Items items = itemRepo.findById(id).get();
//            orderItemRepo.save(OrderItem.builder()
//                    .orders(orders)
//                    .orderPrice(100000)
//                    .orderNumber(32)
//                    .items(items)
//                    .build());
//        });
//    }
//
//    /**
//     * 서비스용 주문상품 단건조회 서비스
//     */
//    public OrderItemTestResDto searchOneOrderItem(Long orderItemId) {
//        return ObjectConverter.toObject(orderItemRepo.findById(orderItemId), OrderItemTestResDto.class);
//    }
//
//    /**
//     * 서비스용 상품생성 서비스
//     */
//    public ItemTestResDto createItem(ItemTestResDto item) {
//        Items entity = item.saveEntity();
//        itemRepo.save(entity);
//
//        return item;
//    }
//
//    public List<Orders> findUserOrder(String userId) {
//        Users users = userRepo.findById(userId).get();
//        return orderRepository.findByUsers(users);
//    }
//
//    public List<OrderItem> findUserOrderItem(Long orderId) {
//        Orders orders = orderRepository.findById(orderId).get();
//        return orderItemRepo.findByOrders(orders);
//    }
    /** 쿠폰등록 */
    public CouponResDto createCoupon(CouponResDto coupon) {
        if (ObjectUtils.isEmpty(coupon)
                || StringUtils.hasText(coupon.getCouponName())) {
        }
        CouponResDto result = ObjectConverter.toObject(couponRepo.save(coupon.saveEntity()), CouponResDto.class);

        return result;
    }
    /** 쿠폰수정 */
    public CouponResDto updateCoupon(CouponResDto coupon) {
        CouponResDto result = ObjectConverter.toObject(couponRepo.save(coupon.saveEntity()), CouponResDto.class);

        return result;
    }
    /** 쿠폰목록조회 */
    public Long deleteCoupon(Long couponId) {

        return null;
    }
    /** 쿠폰상세조회 */
    public List<CouponResDto> multiQueryCoupon(CouponReqDto reqDto) {

        return null;
    }
    /** 쿠폰상세조회 */
    public CouponResDto singleQueryCoupon(Long couponId) {

        return null;
    }
}
