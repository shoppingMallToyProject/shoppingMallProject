package com.openlabs.shoppingmall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponReqDto {
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
}