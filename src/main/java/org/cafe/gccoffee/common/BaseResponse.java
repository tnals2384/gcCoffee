package org.cafe.gccoffee.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"timeStamp", "code", "message", "result"})
public class BaseResponse<T> {
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;


    public static <T> BaseResponse<T> onSuccess(T result) {
        return new BaseResponse<>("200", "요청에 성공하였습니다.", result);
    }
}
