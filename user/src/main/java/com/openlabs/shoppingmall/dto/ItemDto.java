package com.openlabs.shoppingmall.dto;

import com.openlabs.shoppingmall.entity.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemDto extends BaseDto {
    private Integer itemId;
    private String itemName;
    private String itemPrice;
    private Integer discountRate;
    private String itemStock;
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;
}
