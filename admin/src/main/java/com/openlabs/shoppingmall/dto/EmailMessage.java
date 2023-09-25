package com.openlabs.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailMessage {
    private String to; // 수신자
    private String subject; // 메일 제목
    private String message; //메일 내용
}
