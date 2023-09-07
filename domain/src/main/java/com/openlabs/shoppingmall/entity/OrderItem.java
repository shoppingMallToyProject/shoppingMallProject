package com.openlabs.shoppingmall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    /** 상품 연관관계 */
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ITEM_ID")
//    private Items item;
//    @OneToMany(mappedBy = "orderItem", fetch = FetchType.LAZY)
//    private Items item;
    @OneToMany(mappedBy = "orderItem", fetch = FetchType.LAZY)
    private List<Items> item = new ArrayList<>();
    /** 주문 연관관계 */
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ORDER_ID")
//    private Orders order;
    @OneToMany(mappedBy = "orderItem", fetch = FetchType.LAZY)
    private List<Orders> order = new ArrayList<>();
}
