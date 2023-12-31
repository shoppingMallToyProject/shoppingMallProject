package com.openlabs.shoppingmall.dto;

import com.openlabs.shoppingmall.entity.BaseEntity;
import com.openlabs.shoppingmall.entity.OrderStatus;
import com.openlabs.shoppingmall.entity.Users;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDto extends BaseDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private String orderPrice; //TODO 연산 메소드 값 가져와야 함
}
