package com.openlabs.shoppingmall.service;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.exception.ShopException;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.UserCouponsReqDto;
import com.openlabs.shoppingmall.dto.UserCouponsResDto;
import com.openlabs.shoppingmall.dto.UsersReqDto;
import com.openlabs.shoppingmall.dto.UsersResDto;
import com.openlabs.shoppingmall.entity.Coupons;
import com.openlabs.shoppingmall.entity.UserCoupons;
import com.openlabs.shoppingmall.entity.Users;
import com.openlabs.shoppingmall.repository.CouponRepository;
import com.openlabs.shoppingmall.repository.UserCouponRepository;
import com.openlabs.shoppingmall.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import static com.openlabs.shoppingmall.entity.UserCoupons.couponGift;

@Slf4j
@Service
@Validated
public class UsersAdminService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    UserCouponRepository userCouponRepo;
    @Autowired
    CouponRepository couponRepo;

    /**
     * 고객관리
     */
    public UsersResDto updateUser(UsersReqDto reqDto) {
        // 고객관리 조회시 주소, 유저쿠폰 조회(고객상세조회 페이지)
        Users user = userRepo.findById(reqDto.getUserId()).orElseThrow(() -> new ShopException("수정할 고객이 없습니다."));
        Coupons coupons = couponRepo.findById(reqDto.getCouponId()).orElse(null);
        // 고객등급, 고객상태 수정(데이터변동 체크)
        Users changEntity = reqDto.toEntity();
        UsersResDto result = ObjectConverter.toObject(changEntity, UsersResDto.class);

        // 유저에게 쿠폰주기
        if (!userCouponRepo.existsById(reqDto.getCouponId())) {
            userCouponRepo.save(couponGift(user, coupons));
        }
        result.setUserName(user.getUserName());
        result.setUserPw(user.getUserPw());

        userRepo.save(result.toEntity());

        return result;
    }

    /**
     * 쿠폰증정
     */
    public UserCouponsResDto giftCoupon(UserCouponsReqDto reqDto) {
        // 고객, 쿠폰 조회 및 고객쿠폰 DTO생성
        Users userEntity = userRepo.findById(reqDto.getUserId()).orElseThrow(() -> new ShopException("고객 정보가 없습니다."));
        Coupons couponEntity = couponRepo.findById(reqDto.getCouponId()).orElseThrow(() -> new ShopException("쿠폰 정보가 없습니다."));
        UserCoupons userCoupons = couponGift(userEntity, couponEntity);
        // 고객쿠폰 생성
        return ObjectConverter.toObject(userCouponRepo.save(userCoupons), UserCouponsResDto.class);
    }

    /**
     * 고객목록조회
     */
    public Slice<UsersResDto> multiQueryUser(UsersReqDto reqDto, PageDto pageDto) {
        Pageable pageable = PageRequest.of(pageDto.getPageNumber(), pageDto.getSize());

        // 고객명, 고객등급, 고객상태 조회
        if (StringUtils.hasText(reqDto.getUserName())
                && !ObjectUtils.isEmpty(reqDto.getUserRating())
                && !ObjectUtils.isEmpty(reqDto.getUserStatus())){
            return userRepo.findSliceByUserNameAndUserStatusAndUserRating(reqDto.getUserName(), reqDto.getUserStatus(), reqDto.getUserRating(), pageable)
                    .map(user -> ObjectConverter.toObject(user, UsersResDto.class));
        // 전체조회
        }else{
            return userRepo.findSliceBy(pageable).map(user -> ObjectConverter.toObject(user, UsersResDto.class));
        }
    }
    /**
     * 고객상세조회
     */
    public UsersResDto singleQueryUser(UsersReqDto reqDto) {
        // 고객관리 조회시 주소, 유저쿠폰 조회
        Users user = userRepo.findById(reqDto.getUserId()).orElseThrow(() -> new ShopException("조회된 고객이 없습니다."));

        UsersResDto result = ObjectConverter.toObject(user, UsersResDto.class);
        result.setAddresses(user.getAddresses());
        result.setUserCoupons(user.getUserCoupons());

        return result;
    }
}