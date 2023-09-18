package com.openlabs.shoppingmall.service;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.CouponDto;
import com.openlabs.shoppingmall.entity.Coupons;
import com.openlabs.shoppingmall.entity.Users;
import com.openlabs.shoppingmall.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final UserService userService;

    /**
     * 사용자 쿠폰 목록 조회 API
     */
    public Slice<CouponDto> getCouponList(String userId, PageDto pageDto) {
        Users users = userService.getUser(userId);
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getSize());
        return couponRepository.findByUsers(users, pageable)
                .map(coupon -> ObjectConverter.toObject(coupon, CouponDto.class));
    }

    public Coupons getCoupon(long couponId) {
        return couponRepository.findById(couponId).orElse(null);
    }
}
