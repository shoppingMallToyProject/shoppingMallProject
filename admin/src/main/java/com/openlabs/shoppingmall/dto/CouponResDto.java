package com.openlabs.shoppingmall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.openlabs.shoppingmall.entity.Coupons;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponResDto {
    /** 쿠폰ID */
//    @NotBlank
    private Long couponId;
    /** 쿠폰명 */
    @NotBlank
    private String couponName;
    /** 할인가 */
    @NotBlank(message = "할인률은 필수 항목입니다.")
    @Max(value = 100, message = "할인률은 100이상 지성할 수 없습니다.")
    private Integer discountRate;
    /** 이벤트 시작일시 */
    @NotBlank(message = "이벤트시작일시는 필수 항목입니다.")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    private String eventStartTime;
    private LocalDateTime eventStartTime;
    /** 이벤트 종료일시 */
    @NotBlank(message = "이벤트종료일시는 필수 항목입니다.")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    private String eventEndTime;
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