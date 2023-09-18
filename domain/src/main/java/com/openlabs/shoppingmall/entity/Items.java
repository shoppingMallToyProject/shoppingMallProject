package com.openlabs.shoppingmall.entity;

import com.openlabs.framework.exception.ShopException;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@DynamicInsert
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
    private Integer itemPrice;
    /** 재고 */
    @Column(name = "ITEM_STOCK")
    @ColumnDefault(value = "0")
    private Integer itemStock;
    /** 할인률 */
    @Column(name = "DISCOUNT_RATE", length = 3)
    @ColumnDefault(value = "0")
    private Integer discountRate;
    /** 이벤트시작일시 */
    @Column(name = "EVENT_START_TIME")
    private LocalDateTime eventStartTime;
    /** 이벤트종료일시 */
    @Column(name = "EVENT_END_TIME")
    private LocalDateTime eventEndTime;

    /** 할인적용가 */
    @Transient // DB에 맵핑되지 않음
    public Integer getDiscountedItemPrice(){
        if (Objects.nonNull(discountRate) && discountRate != 0) {
            double discountMultiplier = 1.0 - (this.discountRate / 100.0);
            return (int) (this.itemPrice * discountMultiplier);
        }
        return this.itemPrice;
    }
    /** 취소시 수량복구 */
    public Integer addStock(int cancelStock){
        this.itemStock += cancelStock;
        return this.itemStock;
    }
    /** 구매시 수량소모 */
    public Integer removeStock(int sellStock){
        this.itemStock -= sellStock;
        if (this.itemStock <= 0) {
            throw new ShopException("수량이 부족합니다.");
        }
        return this.itemStock;
    }
}
