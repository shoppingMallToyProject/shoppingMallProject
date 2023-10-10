package com.openlabs.shoppingmall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "ORDERS")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders extends BaseEntity {
    /**
     * 주문ID
     */
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long orderId;
    /**
     * 주문일시
     */
    @Column(name = "ORDER_DATE")
    private LocalDateTime orderDate;
    /**
     * 주문상태
     */
    @Column(name = "ORDER_STATUS")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    /**
     * 총 금액
     */
    @Column(name = "TOTAL_PRICE")
    private Integer totalPrice;

    /**
     * 고객
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Users users;

    /**
     * 주문 생성
     */
    public static Orders orderCreate(Users user/*, List<OrderItem> orderItem*/) {
        return Orders.builder()
                .users(user)
                .orderStatus(OrderStatus.ORDER)
                .orderDate(LocalDateTime.now())
//                .totalPrice(getTotalPrice(orderItem))
                .build();
    }

    /**
     * 주문 생성(쿠폰적용)
     */
    public static Orders orderCreate(Users user, List<OrderItem> orderItem, Coupons coupons) {
        return Orders.builder()
                .users(user)
                .orderStatus(OrderStatus.ORDER)
                .orderDate(LocalDateTime.now())
                .totalPrice(getTotalPrice(orderItem, coupons))
                .build();
    }

    /**
     * 주문 취소
     */
    public static void cancel(Orders orders) {
        orders.setOrderStatus(OrderStatus.CANCEL);
    }

    /**
     * 주문 총금액
     */
    public static Integer getTotalPrice(List<OrderItem> orderItem) {
        return orderItem.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }

    /**
     * 주문 총금액(쿠폰적용)
     */
    public static Integer getTotalPrice(List<OrderItem> orderItem, Coupons coupons) {
        return (orderItem.stream().mapToInt(OrderItem::getTotalPrice).sum())
                * coupons.getDiscountRate();
    }

    /**
     * 주문 취소
     */
    public Orders cancel() {
        Orders orders = Orders.builder()
                .orderId(this.orderId)
                .orderStatus(OrderStatus.CANCEL)
                .orderDate(LocalDateTime.now())
                .totalPrice(this.totalPrice)
                .users(this.users)
                .build();
        users.addOrders(orders);
        return orders;
    }
}
