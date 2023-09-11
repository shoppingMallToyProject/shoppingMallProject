package com.openlabs.shoppingmall.service;

import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.CouponReqDto;
import com.openlabs.shoppingmall.dto.CouponResDto;
import com.openlabs.shoppingmall.entity.Coupons;
import com.openlabs.shoppingmall.repository.CouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public CouponResDto createCoupon(CouponReqDto coupon) {

        Coupons entity = Coupons.builder()
                .couponName(coupon.getCouponName())
                .discountRate(coupon.getDiscountRate())
                .eventStartTime(parseDateTimeString(coupon.getEventStartTime()))
                .eventEndTime(parseDateTimeString(coupon.getEventEndTime()))
                .build();
        CouponResDto result = ObjectConverter.toObject(couponRepo.save(entity), CouponResDto.class);

        return result;
    }
    private LocalDateTime parseDateTimeString(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.parse(dateTimeString, formatter);
    }
    /** 쿠폰수정 */
    public CouponResDto updateCoupon(@Valid CouponResDto coupon) {
        CouponResDto result = ObjectConverter.toObject(couponRepo.save(coupon.saveEntity()), CouponResDto.class);

        return result;
    }
    /** 쿠폰삭제 */
    public Long deleteCoupon(@Valid Long couponId) {
        try {
            couponRepo.deleteById(couponId);
        } catch (Exception e) {
            log.error("삭제오류 : {}", e);
        }

        return couponId;
    }
    /** 쿠폰목록조회 */
    public List<CouponResDto> multiQueryCoupon(CouponReqDto reqDto) {
//        log.info("EventStart : {}", reqDto.getEventStartTime());
//        log.info("EventStartType : {}", reqDto.getEventStartTime().getClass().getName());
//        List<CouponResDto> list = new ArrayList<>();
//        Coupons entity = reqDto.queryEntity();
//        if (StringUtils.hasText(reqDto.getCouponName())
//                && reqDto.getDiscountRate() != null) {
//            log.info("쿠폰명과 할인가 조회");
////            list = ObjectConverter.toObject(couponRepo.findByCouponNameAndDiscountRateAndEventStartTime(entity),CouponResDto.class);
//        } else if (StringUtils.hasText(reqDto.getCouponName())) {
//            log.info("쿠폰명 조회");
////            list = ObjectConverter.toObject(couponRepo.findByCouponName(entity),CouponResDto.class);
//        } else if (reqDto.getDiscountRate() != null) {
//            log.info("할인가 조회");
////            list = ObjectConverter.toObject(couponRepo.findByDiscountRate(entity),CouponResDto.class);
//        } else {
//            log.info("전체 조회");
//            list = Collections.singletonList(ObjectConverter.toObject(couponRepo.findAll(), CouponResDto.class));
////            list = entityList.stream().map(this::convertDto).collect(Collectors.toList());
//        }
//        return list;
        return null;
    }
    /** 쿠폰상세조회 */
    public CouponResDto singleQueryCoupon(Long couponId) {

        return null;
    }
    private CouponResDto convertDto(Coupons coupon){
        return CouponResDto.builder()
                .couponId(coupon.getCouponId())
                .couponName(coupon.getCouponName())
                .discountRate(coupon.getDiscountRate())
                .eventStartTime(coupon.getEventStartTime())
                .eventEndTime(coupon.getEventEndTime())
                .build();
    }
}
