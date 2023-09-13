package com.openlabs.shoppingmall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "ORDERS")
@Entity
@Builder
@Getter
@Setter
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
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    /** 고객 */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Users users;

    /** 주문상품 연관관계 */
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ORDERITEM_ID")
//    private OrderItem orderItem;
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<OrderItem> orderItem = new ArrayList<>();

    // 연관관계 메서드
//    public void setMember(Users users) {
//        this.users = users;
//        users.getOrders().add(this);
//    }
}
