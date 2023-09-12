package com.openlabs.shoppingmall.repository;

import com.openlabs.shoppingmall.entity.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupons, Long> {

}
