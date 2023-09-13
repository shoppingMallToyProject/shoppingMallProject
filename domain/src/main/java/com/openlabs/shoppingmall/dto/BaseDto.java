package com.openlabs.shoppingmall.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseDto {
    private String crtrId;
    private LocalDateTime crteTmsp;
    private String edirId;
    private LocalDateTime editTmsp;
}
