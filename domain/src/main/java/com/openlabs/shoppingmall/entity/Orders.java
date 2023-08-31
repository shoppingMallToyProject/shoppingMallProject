package com.openlabs.shoppingmall.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "ORDERS")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders extends BaseEntity {
    /** 주문ID */
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long orderId;
    /** 주문일시 */
    @Column(name = "ORDER_DATE")
    private LocalDateTime orderDate;
    /** 주문상태 */
    @Column(name = "ORDER_STATUS")
    private String orderStatus;

    /** 고객 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Users users;
    /** 주문상품 */
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 연관관계 메서드
    public void setMember(Users users) {
        this.users = users;
        users.getOrders().add(this);
    }
}
