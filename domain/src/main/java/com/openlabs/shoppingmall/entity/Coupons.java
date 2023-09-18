package com.openlabs.shoppingmall.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "COUPONS")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupons extends BaseEntity {
    /** 쿠폰ID */
    @Id @GeneratedValue
    @Column(name = "COUPON_ID")
    private Long couponId;
    /** 쿠폰명 */
    @Column(name = "COUPON_NAME")
    private String couponName;
    /** 할인률 */
    @Column(name = "DISCOUNT_RATE", length = 3)
    private Integer discountRate;
    /** 이벤트시작일시 */
    @Column(name = "EVENT_START_TIME")
    private LocalDateTime eventStartTime;
    /** 이벤트종료일시 */
    @Column(name = "EVENT_END_TIME")
    private LocalDateTime eventEndTime;

    /** 고객쿠폰 연관관계 */
    @OneToMany(mappedBy = "coupons", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserCoupons> userCoupon = new ArrayList<>();
}
