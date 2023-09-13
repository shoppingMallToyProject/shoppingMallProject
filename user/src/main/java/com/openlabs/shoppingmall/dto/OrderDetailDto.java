package com.openlabs.shoppingmall.dto;

import com.openlabs.shoppingmall.entity.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetailDto {
    private long orderId;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private long itemId;
    private int itemNumber;
}
