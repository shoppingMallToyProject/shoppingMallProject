package com.openlabs.shoppingmall.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartDto {
    private Integer itemId;
    private Integer itemNumber;
}
