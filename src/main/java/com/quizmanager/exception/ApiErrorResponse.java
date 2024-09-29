package com.quizmanager.exception;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ApiErrorResponse {
    private String message;
    private int status;

    public ApiErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
