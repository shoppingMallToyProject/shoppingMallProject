package com.openlabs.shoppingmall.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openlabs.shoppingmall.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDetailResDto {
    /** 주문ID */
    @NotBlank(message = "이벤트종료일시는 필수 항목입니다.")
    private Long orderId;
    /** 주문일시 */
    @NotBlank(message = "주문일시는 필수 항목입니다.")
    private LocalDateTime orderDate;
    /** 주문상태 */
    @NotBlank(message = "주문상태는 필수 항목입니다.")
    private OrderStatus orderStatus;
    /** 재고 */
    @NotBlank(message = "재고는 필수 항목입니다.")
    private Integer totalPrice;
    /** 고객 */
    private Users users;
    /** 주문상품 */
    private List<OrderItem> orderItem;
    /** 싱픔 */
    private List<Items> items;
}