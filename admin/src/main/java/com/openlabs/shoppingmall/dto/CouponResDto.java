package com.openlabs.shoppingmall.dto;

import com.openlabs.shoppingmall.entity.Coupons;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponResDto {
    /** 쿠폰ID */
    private Long couponId;
    /** 쿠폰명 */
    private String couponName;
    /** 할인가 */
    private Integer discountRate;
    /** 이벤트 시작일시 */
    private LocalDateTime eventStartTime;
    /** 이벤트 종료일시 */
    private LocalDateTime eventEndTime;

    public Coupons saveEntity(){
        return Coupons.builder()
                .couponId(this.couponId)
                .couponName(this.couponName)
                .discountRate(this.discountRate)
                .eventStartTime(this.eventStartTime)
                .eventEndTime(this.eventEndTime)
                .build();
    }
}