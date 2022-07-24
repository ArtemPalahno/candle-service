package com.palahno.candleservice.exception;

/**
 * @author Artem Palahno
 */
public record ExceptionDto(int status, String error, String message) {
}