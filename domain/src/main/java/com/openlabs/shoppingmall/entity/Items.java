package com.openlabs.shoppingmall.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "ITEMS")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Items extends BaseEntity {
    /** 상품ID */
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long itemId;
    /** 상품명 */
    @Column(name = "ITEM_NAME")
    private String itemName;
    /** 상품가 */
    @Column(name = "ITEM_PRICE")
    private String itemPrice;
    /** 재고 */
    @Column(name = "ITEM_STOCK")
    private String itemStock;
    /** 할인률 */
    @Column(name = "DISCOUNT_RATE")
    private Integer discountRate;
    /** 이벤트시작일시 */
    @Column(name = "EVENT_START_TIME")
    private LocalDateTime eventStartTime;
    /** 이벤트종료일시 */
    @Column(name = "EVENT_END_TIME")
    private LocalDateTime eventEndTime;

    /** 주문상품 연관관계 */
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ORDERITEM_ID")
//    private OrderItem orderItem;

//    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
//    private List<OrderItem> orderItem = new ArrayList<>();

}
