package com.openlabs.shoppingmall.repository;

import com.openlabs.shoppingmall.entity.Coupons;
import com.openlabs.shoppingmall.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CouponRepository extends JpaRepository<Coupons, Long> {
    Slice<Coupons> findSliceByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndCouponNameContainingAndDiscountRateIs(LocalDateTime eventStartTime, LocalDateTime eventEndTime, String couponName, Integer discountRate, Pageable pageable);

    Slice<Coupons> findSliceByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndCouponNameContaining(LocalDateTime eventStartTime, LocalDateTime eventEndTime, String couponName, Pageable pageable);

    Slice<Coupons> findSliceByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndDiscountRateIs(LocalDateTime eventStartTime, LocalDateTime eventEndTime, Integer discountRate, Pageable pageable);

    Slice<Coupons> findSliceBy(Pageable pageable);

    Slice<Coupons> findByUsers(Users users, Pageable pageable);
}
