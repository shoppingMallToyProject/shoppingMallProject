package com.openlabs.shoppingmall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

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
    private Integer orderPrice;
    /** 주문상품수량 */
    @Column(name = "ORDER_NUMBER")
    private Integer orderNumber;

    /** 상품 연관관계 */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Items items;
    /** 주문 연관관계 */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Orders orders;

    /** 주문상품 생성 */
    public static OrderItem createOrderItem(Items item, Orders order, int orderPrice, int orderNumber){
        OrderItem orderItem = OrderItem.builder()
                .orderPrice(orderPrice)
                .orderNumber(orderNumber)
                .items(item)
                .orders(order)
                .build();

        item.removeStock(orderNumber);
        return orderItem;
    }
    /** 주문상품 취소 */
    public OrderItem cancel(){
        OrderItem orderItem = OrderItem.builder()
                .orderPrice(orderPrice)
                .orderNumber(orderNumber)
                .build();

        getItems().addStock(orderNumber);
        return orderItem;
    }
    /** 주문상품 전체가격 */
    public Integer getTotalPrice(Items items){
        if (Objects.nonNull(items.getDiscountRate()) && items.getDiscountRate() != 0) {
            this.orderPrice = (this.items.getItemPrice() * (this.items.getDiscountRate() / 100))
                    * this.orderNumber;
        } else {
            this.orderPrice = this.items.getItemPrice() * this.orderNumber;
        }
        return this.orderPrice;
    }
}
