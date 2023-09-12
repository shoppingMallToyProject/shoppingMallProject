package com.openlabs.shoppingmall.service;

import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.CouponReqDto;
import com.openlabs.shoppingmall.dto.CouponResDto;
import com.openlabs.shoppingmall.entity.Coupons;
import com.openlabs.shoppingmall.repository.CouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
        }

        return couponId;
    }

    /**
     * 쿠폰목록조회
     */
    public List<CouponResDto> multiQueryCoupon(@Valid CouponReqDto reqDto) {
        List<CouponResDto> list = new ArrayList<>();
        Coupons entity = reqDto.toEntity();
        if (StringUtils.hasText(reqDto.getCouponName())
                && reqDto.getDiscountRate() != null) {
            List<Coupons> tmp = couponRepo.findByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndCouponNameLikeAndDiscountRateIs(entity.getEventStartTime(), entity.getEventEndTime(), entity.getCouponName(), entity.getDiscountRate());
            tmp.forEach(coupons -> list.add(convertDto(coupons)));
        } else if (StringUtils.hasText(reqDto.getCouponName())) {
            List<Coupons> tmp = couponRepo.findByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndCouponNameLike(entity.getEventStartTime(), entity.getEventEndTime(), entity.getCouponName());
            tmp.forEach(coupons -> list.add(convertDto(coupons)));
        } else if (reqDto.getDiscountRate() != null) {
            List<Coupons> tmp = couponRepo.findByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndDiscountRateIs(entity.getEventStartTime(), entity.getEventEndTime(), entity.getDiscountRate());
            tmp.forEach(coupons -> list.add(convertDto(coupons)));
        } else {
            List<Coupons> tmp = couponRepo.findAll();
            tmp.forEach(coupons -> list.add(convertDto(coupons)));
        }
        return list;
    }

    /**
     * 쿠폰상세조회
     */
    public CouponResDto singleQueryCoupon(@Valid Long couponId) {
        return ObjectConverter.toObject(couponRepo.findById(couponId), CouponResDto.class);
    }
    /**  */
    private CouponResDto convertDto(Coupons coupon) {
        return CouponResDto.builder()
                .couponId(coupon.getCouponId())
                .couponName(coupon.getCouponName())
                .discountRate(coupon.getDiscountRate())
                .eventStartTime(coupon.getEventStartTime())
                .eventEndTime(coupon.getEventEndTime())
                .build();
    }
}