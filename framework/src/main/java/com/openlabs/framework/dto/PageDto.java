package com.openlabs.framework.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageDto {
    private int size;
    private int pageNumber;
}
