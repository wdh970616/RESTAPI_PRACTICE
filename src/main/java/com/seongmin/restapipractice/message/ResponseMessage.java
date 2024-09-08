package com.seongmin.restapipractice.message;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseMessage {
    private int httpStatusCode;
    private String message;
    private Object result;
}
