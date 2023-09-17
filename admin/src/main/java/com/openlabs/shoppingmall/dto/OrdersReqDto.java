package com.openlabs.shoppingmall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.openlabs.framework.dto.PageDto;
import com.openlabs.shoppingmall.entity.Items;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersReqDto extends PageDto{
    /** 상품ID */
    private Long itemId;
    /** 상품명 */
//    @NotBlank(message = "상품명은 필수항목입니다.")
    private String itemName;
    /** 상품가 */
//    @NotNull(message = "상품가는 필수항목입니다.")
    private Integer itemPrice;
    /** 재고 */
//    @NotNull(message = "상품재고는 필수항목입니다.")
    private Integer itemStock;
    /** 할인률 */
    @Max(value = 100)
    private Integer discountRate;
    /** 이벤트시작일시 */
    @NotBlank(message = "이벤트시작일시는 필수항목입니다.")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String eventStartTime;
    /** 이벤트종료일시 */
    @NotBlank(message = "이벤트종료일시는 필수항목입니다.")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String eventEndTime;

    public Items toEntity(){
        LocalDateTime startTime = parseDateTimeString(this.eventStartTime);
        LocalDateTime endTime = parseDateTimeString(this.eventEndTime);
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