package com.openlabs.shoppingmall.repository;

import com.openlabs.shoppingmall.entity.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupons, Long> {
    List<Coupons> findByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndCouponNameLikeAndDiscountRateIs(LocalDateTime startTime, LocalDateTime endTime, String couponName, Integer discountRate);

    List<Coupons> findByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndCouponNameLike(LocalDateTime eventStartTime, LocalDateTime eventEndTime, String couponName);

    List<Coupons> findByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndDiscountRateIs(LocalDateTime eventStartTime, LocalDateTime eventEndTime, Integer discountRate);
}
