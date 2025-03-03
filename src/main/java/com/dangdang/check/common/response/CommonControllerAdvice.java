package com.dangdang.check.common.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class CommonControllerAdvice {

    /**
     * Handles IllegalArgumentException.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public CommonResponse<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        if (ex.getMessage() == null) {
            return CommonResponse.fail(ErrorCode.COMMON_ILLEGAL_STATUS);
        }
        log.error("IllegalArgumentException: {}", ex.getMessage());
        return CommonResponse.fail(ex.getMessage(), ErrorCode.COMMON_ILLEGAL_STATUS.name());
    }

    /**
     * Handles RuntimeException.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public CommonResponse<?> handleRuntimeException(RuntimeException ex) {
        if (ex.getMessage() == null) {
            return CommonResponse.fail(ErrorCode.COMMON_INVALID_PARAMETER);
        }

        log.error("RuntimeException: {}", ex.getMessage());
        return CommonResponse.fail(ex.getMessage(), ErrorCode.COMMON_INVALID_PARAMETER.name());
    }
}