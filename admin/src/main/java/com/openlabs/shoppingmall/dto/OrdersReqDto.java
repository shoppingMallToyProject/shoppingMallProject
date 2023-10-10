package com.openlabs.shoppingmall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersReqDto{
    /** 주문ID */
    @NotBlank(message = "주문ID는 필수 항목입니다.")
    private Long orderId;
//    /** 주문일시 */
//    private LocalDateTime orderDate;
//    /** 주문상태 */
//    private OrderStatus orderStatus;
//    /** 재고 */
//    private Integer totalPrice;
}