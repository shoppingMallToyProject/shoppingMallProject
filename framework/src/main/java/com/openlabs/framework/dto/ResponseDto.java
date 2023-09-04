package com.openlabs.framework.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDto<T> {
    private int status;
    private String message;
    private T data;
    private LocalDateTime timeStamp;
    private List<String> errors;

    public static <T> ResponseDto<T> ok(T data) {
        return ResponseDto.<T>builder()
                .status(HttpStatus.OK.value())
                .message("Success")
                .data(data)
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public static <T> ResponseDto<T> error(int status, String message, T data, List<String> errors) {
        return ResponseDto.<T>builder()
                .status(status)
                .message(message)
                .data(data)
                .errors(errors)
                .timeStamp(LocalDateTime.now())
                .build();
    }
}
