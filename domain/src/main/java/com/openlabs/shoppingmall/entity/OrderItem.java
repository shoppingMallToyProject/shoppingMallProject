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
    @Column(name = "TOTAL_PRICE")
    private Integer totalPrice;
    /** 주문상품수량 */
    @Column(name = "ORDER_QUANTITY")
    private Integer orderQuantity;

    /** 상품 연관관계 */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
//    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Items items;
    /** 주문 연관관계 */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Orders orders;

    /** 주문상품 생성 */
    public static OrderItem createOrderItem(Items item, Orders order, int orderNumber){
        OrderItem orderItem = OrderItem.builder()
                .totalPrice(getTotalPrice(item, orderNumber))
                .orderQuantity(orderNumber)
                .items(item)
                .orders(order)
                .build();

        item.removeStock(orderNumber);
        return orderItem;
    }
    /** 주문상품 취소 */
    public OrderItem cancel(Items item){
        OrderItem orderItem = OrderItem.builder()
                .totalPrice(getTotalPrice(item, this.orderQuantity))
                .orderQuantity(this.orderQuantity)
                .build();

        getItems().addStock(this.orderQuantity);
        return orderItem;
    }
    /** 주문상품 전체가격 */
    public static Integer getTotalPrice(Items items, int orderNumber){
        if (Objects.nonNull(items.getDiscountRate()) && items.getDiscountRate() != 0) {
            return  (items.getItemPrice() * (items.getDiscountRate() / 100)) * orderNumber;
        } else {
            return items.getItemPrice() * orderNumber;
        }
    }
}
