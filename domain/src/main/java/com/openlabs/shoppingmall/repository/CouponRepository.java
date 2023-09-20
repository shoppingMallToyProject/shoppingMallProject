package com.openlabs.shoppingmall.repository;

import com.openlabs.shoppingmall.entity.Coupons;
import com.openlabs.shoppingmall.entity.Items;
import com.openlabs.shoppingmall.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupons, Long> {
    /** 쿠폰목록조회(이벤트시작,종료일시, 쿠폰명, 쿠폰할인률 조건검색) */
    Slice<Coupons> findSliceByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndCouponNameContainingAndDiscountRateIs(LocalDateTime eventStartTime, LocalDateTime eventEndTime, String couponName, Integer discountRate, Pageable pageable);
    /** 쿠폰목록조회(이벤트시작,종료일시, 쿠폰명 조건검색) */
    Slice<Coupons> findSliceByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndCouponNameContaining(LocalDateTime eventStartTime, LocalDateTime eventEndTime, String couponName, Pageable pageable);
    /** 쿠폰목록조회(이벤트시작,종료일시, 쿠폰할인률 조건검색) */
    Slice<Coupons> findSliceByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndDiscountRateIs(LocalDateTime eventStartTime, LocalDateTime eventEndTime, Integer discountRate, Pageable pageable);
    /** 쿠폰목록조회(이벤트시작,종료일시 조건검색) */
    Slice<Coupons> findSliceBy(Pageable pageable);
    /** 쿠폰명 단건조회(중복체크) */
    Coupons findByCouponName(String couponName);

}
