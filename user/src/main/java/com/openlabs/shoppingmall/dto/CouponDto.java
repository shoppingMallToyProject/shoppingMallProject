package com.openlabs.shoppingmall.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponDto extends BaseDto{
    private Long couponId;
    private String couponName;
    private Integer discountRate;
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;
}
