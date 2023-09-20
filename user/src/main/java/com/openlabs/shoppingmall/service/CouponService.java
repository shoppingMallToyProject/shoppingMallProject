package com.openlabs.shoppingmall.service;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.CouponDto;
import com.openlabs.shoppingmall.entity.Coupons;
import com.openlabs.shoppingmall.entity.UserCoupons;
import com.openlabs.shoppingmall.entity.Users;
import com.openlabs.shoppingmall.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final UserService userService;

    /**
     * 사용자 쿠폰 목록 조회 API
     */
    public List<CouponDto> getCouponList(String userId) {
        Users users = userService.getUser(userId);
        List<UserCoupons> userCoupons = users.getUserCoupons();

        return userCoupons.stream().map(coupon ->
                ObjectConverter.toObject(coupon.getCoupons(), CouponDto.class)).collect(Collectors.toList());
    }

    public Coupons getCoupon(long couponId) {
        return couponRepository.findById(couponId).orElse(null);
    }
}
