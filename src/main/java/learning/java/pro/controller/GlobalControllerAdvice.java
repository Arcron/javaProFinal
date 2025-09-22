package learning.java.pro.controller;

import learning.java.pro.dto.ErrorResponseDto;
import learning.java.pro.excetpion.LowDailyLimitException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleRuntimeException(RuntimeException ex) {
        return new ErrorResponseDto(ex.getMessage());
    }

    @ExceptionHandler(LowDailyLimitException.class)
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public ErrorResponseDto handleLowDailyLimitException(LowDailyLimitException ex) {
        return new ErrorResponseDto(ex.getMessage());
    }
}
