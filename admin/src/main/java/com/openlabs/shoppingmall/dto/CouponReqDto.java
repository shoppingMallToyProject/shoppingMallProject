package com.openlabs.shoppingmall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponReqDto {
    /** 쿠폰명 */
    private String couponName;
    /** 할인가 */
    @Max(value = 100)
    private Integer discountRate;
    /** 이벤트 시작일시 */
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String eventStartTime;
//    private LocalDateTime eventStartTime;
    /** 이벤트 종료일시 */
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String eventEndTime;
//    private LocalDateTime eventEndTime;

//    public Coupons queryEntity(){
//        return Coupons.builder()
//                .couponName(this.couponName)
//                .discountRate(this.discountRate)
//                .eventStartTime(this.eventStartTime)
//                .eventEndTime(this.eventEndTime)
//                .build();
//    }
}