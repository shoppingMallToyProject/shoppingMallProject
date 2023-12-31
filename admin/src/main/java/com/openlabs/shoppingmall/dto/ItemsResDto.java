package com.openlabs.shoppingmall.dto;

import com.openlabs.shoppingmall.entity.Items;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemsResDto {
    /** 상품ID */
    @ApiModelProperty(value = "testUser", example = "상품ID")
    private Long itemId;
    /** 상품명 */
    @ApiModelProperty(example = "testItem", value = "상품명")
    @NotBlank(message = "상품명은 필수항목입니다.")
    private String itemName;
    /** 상품가 */
    @ApiModelProperty(example = "10000", value = "상품가")
    @NotNull(message = "상품가는 필수항목입니다.")
    private Integer itemPrice;
    /** 재고 */
    @ApiModelProperty(example = "100", value = "재고")
    @NotNull(message = "상품재고는 필수항목입니다.")
    private Integer itemStock;
    /** 할인률 */
    @ApiModelProperty(example = "0", value = "할인률")
    private Integer discountRate;
    /** 이벤트시작일시 */
    @ApiModelProperty(example = "2023-09-01T09:00:00", value = "이벤트시작일시")
    @NotBlank(message = "이벤트시작일시는 필수 항목입니다.")
    private LocalDateTime eventStartTime;
    /** 이벤트종료일시 */
    @ApiModelProperty(example = "2023-10-01T09:00:00", value = "이벤트종료일시")
    @NotBlank(message = "이벤트종료일시는 필수 항목입니다.")
    private LocalDateTime eventEndTime;

    public Items toEntity(){
        return Items.builder()
                .itemId(this.itemId)
                .itemName(this.itemName)
                .itemPrice(this.itemPrice)
                .itemStock(this.itemStock)
                .discountRate(this.discountRate)
                .eventStartTime(this.eventStartTime)
                .eventEndTime(this.eventEndTime)
                .build();
    }
}