package com.openlabs.shoppingmall.dto;

import com.openlabs.shoppingmall.entity.BaseEntity;
import com.openlabs.shoppingmall.entity.Items;
import com.openlabs.shoppingmall.entity.Orders;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemDto extends BaseDto {
    private Long orderItemId;
    private Long orderPrice;
    private Long orderNumber;
    private Items items;
    private Orders orders;
}
