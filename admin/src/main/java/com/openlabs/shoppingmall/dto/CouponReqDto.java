package com.openlabs.shoppingmall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.openlabs.shoppingmall.entity.Coupons;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponReqDto {
    /** 쿠폰명 */
    private Long couponId;
    /** 쿠폰명 */
    private String couponName;
    /** 할인가 */
    @Max(value = 100)
    private Integer discountRate;
    /** 이벤트 시작일시 */
    @NotBlank(message = "이벤트시작일시는 필수항목입니다.")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String eventStartTime;
    /** 이벤트 종료일시 */
    @NotBlank(message = "이벤트종료일시는 필수항목입니다.")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String eventEndTime;

    public Coupons toEntity(){
        LocalDateTime startTime = parseDateTimeString(this.eventStartTime);
        LocalDateTime endTime = parseDateTimeString(this.eventEndTime);
        return Coupons.builder()
                .couponId(this.couponId)
                .couponName(this.couponName)
                .discountRate(this.discountRate)
                .eventStartTime(startTime)
                .eventEndTime(endTime)
                .build();
    }
    /** StringToLocalDateTime */
    private LocalDateTime parseDateTimeString(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(dateTimeString, formatter);
    }
}