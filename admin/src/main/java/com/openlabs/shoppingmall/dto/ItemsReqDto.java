package com.openlabs.shoppingmall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.openlabs.shoppingmall.entity.Items;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Max;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemsReqDto{
    /** 상품ID */
    @ApiModelProperty(value = "상품ID", example = "7")
    private Long itemId;
    /** 상품명 */
//    @NotBlank(message = "상품명은 필수항목입니다.")
    @ApiModelProperty(value = "상품명", example = "아맛나")
    private String itemName;
    /** 상품가 */
//    @NotBlank(message = "상품가는 필수항목입니다.")
    @ApiModelProperty(value = "상품가", example = "1000")
    private Integer itemPrice;
    /** 재고 */
//    @NotBlank(message = "상품재고는 필수항목입니다.")
    @ApiModelProperty(value = "재고", example = "100")
    private Integer itemStock;
    /** 할인률 */
    @Max(value = 100)
    @ApiModelProperty(value = "할인률", example = "5")
    private Integer discountRate;
    /** 이벤트시작일시 */
//    @NotBlank(message = "이벤트시작일시는 필수항목입니다.")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @ApiModelProperty(value = "이벤트시작일시", example = "2023-09-01T09:00:00")
    private String eventStartTime;
    /** 이벤트종료일시 */
//    @NotBlank(message = "이벤트종료일시는 필수항목입니다.")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @ApiModelProperty(value = "이벤트종료일시", example = "2023-10-01T09:00:00")
    private String eventEndTime;

    public Items toEntity(){
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (StringUtils.hasText(this.eventStartTime)) startTime = parseDateTimeString(this.eventStartTime);
        if (StringUtils.hasText(this.eventEndTime)) endTime = parseDateTimeString(this.eventEndTime);
        return Items.builder()
                .itemId(this.itemId)
                .itemName(this.itemName)
                .itemPrice(this.itemPrice)
                .itemStock(this.itemStock)
                .discountRate(this.discountRate)
                .eventStartTime(startTime)
                .eventEndTime(endTime)
                .build();
    }
    /** StringToLocalDateTime */
    private LocalDateTime parseDateTimeString(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(dateTimeString, formatter);
    }
}