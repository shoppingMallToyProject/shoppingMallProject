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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

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
        Users entity = userRepo.findById(reqDto.getUserId()).orElseThrow(() -> new ShopException("수정할 고객이 없습니다."));
        // 고객등급, 고객상태 수정(데이터변동 체크)
//        reqDto.setUserRating();
//        reqDto.setUserStatus();
        // 유저에게 쿠폰주기
//        if (!ObjectUtils.isEmpty(reqDto.getUserCoupons())) {
//            List<UserCoupons> tmpList = new ArrayList<>();
//            reqDto.getUserCoupons().forEach(dto -> {
//                UserCoupons tmp = UserCoupons.builder()
//                        .users(reqDto.toEntity())
//                        .coupons()
//                        .useYn("N")
//                        .build();
//            });
//        }
        return null;
//        return ObjectConverter.toObject(userRepo.save(reqDto.toEntity()), UsersResDto.class);
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
    public Page<UsersResDto> multiQueryUser(UsersReqDto reqDto, PageDto pageDto) {
        // 고객ID, 고객명, 고객등급, 고객상태 조회
        // 고객ID, 고객명, 고객등급조회
        // 고객ID, 고객명 조회
        // 고객ID 조회
        // 고객명, 고객등급, 고객상태 조회
        // 고객명, 고객등급 조회
        // 고객명 조회
        // 고객등급, 고객상태 조회
        // 고객상태 조회
        // 전체조회
        List<UsersResDto> list = new ArrayList<>();
        List<Users> tmp = userRepo.findAll();
        tmp.forEach(user -> list.add(convertDto(user)));
        Long count = userRepo.count();
        Pageable pageable = PageRequest.of(reqDto.getPageNumber(), reqDto.getSize());

        return new PageImpl<>(list, pageable, count);
    }
    /**
     * 고객상세조회
     */
    public UsersResDto singleQueryUser(UsersReqDto reqDto) {
        // 고객관리 조회시 주소, 유저쿠폰 조회

        return null;
    }

    /** EntityToDTO */
    private UsersResDto convertDto(Users user) {
        return UsersResDto.builder()
                .userId(user.getUserId())
                .userPw(user.getUserPw())
                .userName(user.getUserName())
                .userRating(user.getUserRating())
                .userStatus(user.getUserStatus())
                .build();
    }


}