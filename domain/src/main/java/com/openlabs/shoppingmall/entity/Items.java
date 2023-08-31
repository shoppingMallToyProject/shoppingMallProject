package com.openlabs.shoppingmall.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ITEMS")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Items extends BaseEntity {
    /** 상품ID */
    @Id @Column(name = "ITEM_ID")
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
}
