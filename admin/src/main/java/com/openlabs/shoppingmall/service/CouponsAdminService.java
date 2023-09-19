package com.openlabs.shoppingmall.service;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.exception.ShopException;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.CouponReqDto;
import com.openlabs.shoppingmall.dto.CouponResDto;
import com.openlabs.shoppingmall.entity.Coupons;
import com.openlabs.shoppingmall.entity.Items;
import com.openlabs.shoppingmall.repository.CouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Service
@Validated
public class CouponsAdminService {
    @Autowired
    CouponRepository couponRepo;

    /**
     * 쿠폰등록
     */
    public CouponResDto createCoupon(@Valid CouponReqDto reqDto) {
        Optional<Items> entity = couponRepo.findByCouponName(reqDto.getCouponName());
        if (entity.get().getItemName().equals(reqDto.getCouponName())) {
            log.info("같은 이름의 쿠폰이 있습니다. => itemName : {}", entity.get().getItemName());
            throw new ShopException("같은 이름의 쿠폰이 있습니다.");
        }

        return ObjectConverter.toObject(couponRepo.save(reqDto.toEntity()), CouponResDto.class);
    }

    /**
     * 쿠폰수정
     */
    public CouponResDto updateCoupon(@Valid CouponReqDto reqDto) {
        CouponReqDto existedDto = ObjectConverter.toObject(couponRepo.findById(reqDto.getCouponId()), CouponReqDto.class);
        if(ObjectUtils.isEmpty(existedDto)) throw new ShopException("데이터가 없습니다.");
        if(existedDto.equals(reqDto))   throw new ShopException("데이터를 변경해 주세요");
        return ObjectConverter.toObject(couponRepo.save(reqDto.toEntity()), CouponResDto.class);
    }

    /**
     * 쿠폰삭제
     */
    public Long deleteCoupon(@Valid Long couponId) {
        if (!couponRepo.existsById(couponId)) throw new ShopException("삭제할 쿠폰이 없습니다.");
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
    public Slice<CouponResDto> multiQueryCoupon(CouponReqDto reqDto, PageDto pageDto) {
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getSize());

        Coupons entity = reqDto.toEntity();
        // 쿠폰명, 할인률, 시작, 종료일시 조회
        if (StringUtils.hasText(reqDto.getCouponName())
                && reqDto.getDiscountRate() != null) {
            return couponRepo.findSliceByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndCouponNameContainingAndDiscountRateIs(entity.getEventStartTime(), entity.getEventEndTime(), entity.getCouponName(), entity.getDiscountRate(), pageable)
                    .map(coupons -> ObjectConverter.toObject(coupons, CouponResDto.class));
        }// 쿠폰명, 시작, 종료일시 조회
        else if (StringUtils.hasText(reqDto.getCouponName())) {
            return couponRepo.findSliceByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndCouponNameContaining(entity.getEventStartTime(), entity.getEventEndTime(), entity.getCouponName(), pageable)
                    .map(coupons -> ObjectConverter.toObject(coupons, CouponResDto.class));
        }// 할인률 , 시작, 종료일시 조회
        else if (reqDto.getDiscountRate() != null) {
            return  couponRepo.findSliceByEventStartTimeGreaterThanEqualAndEventEndTimeLessThanEqualAndDiscountRateIs(entity.getEventStartTime(), entity.getEventEndTime(), entity.getDiscountRate(), pageable)
                    .map(coupons -> ObjectConverter.toObject(coupons, CouponResDto.class));
        }// 전체조회
        else {
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