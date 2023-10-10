package com.openlabs.shoppingmall.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openlabs.shoppingmall.entity.OrderStatus;
import com.openlabs.shoppingmall.entity.Orders;
import com.openlabs.shoppingmall.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResDto {
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
    @JsonIgnore
    private Users users;

    public Orders toEntity(){
        return Orders.builder()
                .orderId(this.orderId)
                .orderDate(this.orderDate)
                .orderStatus(this.orderStatus)
                .totalPrice(this.totalPrice)
                .users(this.users)
                .build();
    }
}