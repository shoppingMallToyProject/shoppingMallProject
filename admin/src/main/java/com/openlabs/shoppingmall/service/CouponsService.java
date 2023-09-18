package com.openlabs.shoppingmall.service;

import com.openlabs.framework.exception.ShopException;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.CouponReqDto;
import com.openlabs.shoppingmall.dto.CouponResDto;
import com.openlabs.shoppingmall.entity.Coupons;
import com.openlabs.shoppingmall.repository.CouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Slf4j
@Service
@Validated
public class CouponsService {
    @Autowired
    CouponRepository couponRepo;

    /**
     * 쿠폰등록
     */
    public CouponResDto createCoupon(@Valid CouponReqDto coupon) {
        return ObjectConverter.toObject(couponRepo.save(coupon.toEntity()), CouponResDto.class);
    }

    /**
     * 쿠폰수정
     */
    public CouponResDto updateCoupon(@Valid CouponReqDto coupon) {
        return ObjectConverter.toObject(couponRepo.save(coupon.toEntity()), CouponResDto.class);
    }

    /**
     * 쿠폰삭제
     */
    public Long deleteCoupon(@Valid Long couponId) {
        try {
            couponRepo.deleteById(couponId);
        } catch (Exception e) {
            log.error("삭제오류 : {}", e);
            new ShopException("삭제 실패");
        }

        return couponId;
    }

    /**
     * 쿠폰목록조회
     */
    public Slice<CouponResDto> multiQueryCoupon(CouponReqDto reqDto
//            , PageDto pageDto
            , int page
            , int size) {
        Pageable pageable = PageRequest.of(page,size);

        Coupons entity = reqDto.toEntity();
        if (StringUtils.hasText(reqDto.getCouponName())
                && reqDto.getDiscountRate() != null) {
            return couponRepo.findSliceByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndCouponNameContainingAndDiscountRateIs(entity.getEventStartTime(), entity.getEventEndTime(), entity.getCouponName(), entity.getDiscountRate(), pageable)
                    .map(coupons -> ObjectConverter.toObject(coupons, CouponResDto.class));
        } else if (StringUtils.hasText(reqDto.getCouponName())) {
            return couponRepo.findSliceByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndCouponNameContaining(entity.getEventStartTime(), entity.getEventEndTime(), entity.getCouponName(), pageable)
                    .map(coupons -> ObjectConverter.toObject(coupons, CouponResDto.class));
        } else if (reqDto.getDiscountRate() != null) {
            return  couponRepo.findSliceByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndDiscountRateIs(entity.getEventStartTime(), entity.getEventEndTime(), entity.getDiscountRate(), pageable)
                    .map(coupons -> ObjectConverter.toObject(coupons, CouponResDto.class));
        } else {
            return couponRepo.findSliceBy(pageable)
                    .map(coupons -> ObjectConverter.toObject(coupons, CouponResDto.class));
        }
    }

    /**
     * 쿠폰상세조회
     */
    public CouponResDto singleQueryCoupon(@Valid Long couponId) {
        return ObjectConverter.toObject(couponRepo.findById(couponId), CouponResDto.class);
    }
}