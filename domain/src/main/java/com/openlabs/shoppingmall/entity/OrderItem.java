package com.openlabs.shoppingmall.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "ORDERITEM")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity {
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

    /** 상품DTO */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Items items;
    /** 주문DTO */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Orders orders;
}