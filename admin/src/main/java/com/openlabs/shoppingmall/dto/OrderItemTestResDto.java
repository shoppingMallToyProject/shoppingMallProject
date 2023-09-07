package com.openlabs.shoppingmall.dto;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.openlabs.shoppingmall.entity.Items;
import com.openlabs.shoppingmall.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemTestResDto {
    private Long orderItemId;
    private Long orderPrice;
    private Long orderNumber;


    private List<Items> item = new ArrayList<>();
    private List<Orders> order = new ArrayList<>();
}
