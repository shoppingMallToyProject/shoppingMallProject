package com.openlabs.shoppingmall.dto;

import com.openlabs.shoppingmall.entity.Coupons;
import com.openlabs.shoppingmall.entity.UserCoupons;
import com.openlabs.shoppingmall.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCouponsResDto{
    /** 고객쿠폰ID */
    private Long usercouponId;
    /** 사용일자 */
    private LocalDateTime useDate;
    /** 사용여부 */
    private String useYn;

    /** 고객 연관관계 */
    private Users users;
    /** 쿠폰 연관관계 */
    private Coupons coupons;

    public UserCoupons toEntity(){
        return UserCoupons.builder()
                .usercouponId(this.usercouponId)
                .useDate(this.useDate)
                .useYn(this.useYn)
                .users(this.users)
                .coupons(this.coupons)
                .build();
    }
}