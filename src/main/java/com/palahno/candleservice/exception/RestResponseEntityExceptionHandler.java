package com.palahno.candleservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Artem Palahno
 */
@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({NoSuchCandlesException.class})
    public ResponseEntity<Object> noSuchEntityException(
            NoSuchCandlesException ex) {
        log.error("No such entity: {}", ex.getMessage());
        return buildResponseEntity(HttpStatus.NOT_FOUND, "No such entity");
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<Object> missingParameterException(
            MissingServletRequestParameterException ex) {
        log.error("Parameter is missed: {}", ex.getParameterName());
        return buildResponseEntity(HttpStatus.BAD_REQUEST, String.format("Parameter '%s' is missed", ex.getParameterName()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGeneralException(
            Exception ex) {
        log.error("Error is occurred: {}", ex.getMessage());
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, String errorMsg) {
        ExceptionDto error = new ExceptionDto(httpStatus.value(), httpStatus.getReasonPhrase(), errorMsg);
        return new ResponseEntity<>(error, new HttpHeaders(), error.status());
    }

}
