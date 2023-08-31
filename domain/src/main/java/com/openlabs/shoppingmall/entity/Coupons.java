package com.openlabs.shoppingmall.entity;

import lombok.*;

import javax.persistence.*;

//@Table(name = "COUPONS")
//@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupons extends BaseEntity {
    /** 주문상품ID */
    @Id @GeneratedValue
    @Column(name = "ORDERITEM_ID")
    private Long orderItemId;
    /** 주문상품가 */
    @Column(name = "ORDER_PRICE")
    private Long orderPrice;
    /** 주문상품수량 */
    @Column(name = "ORDER_NUMBER")
    private Long orderNumber;

//    /** 상품DTO */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ITEM_ID")
//    private Items items;
}
