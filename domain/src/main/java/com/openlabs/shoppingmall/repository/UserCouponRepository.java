package com.openlabs.shoppingmall.repository;

import com.openlabs.shoppingmall.entity.UserCoupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCouponRepository extends JpaRepository<UserCoupons, Long> {
}
