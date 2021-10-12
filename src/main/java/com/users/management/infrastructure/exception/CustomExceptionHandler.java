package com.users.management.infrastructure.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.users.management.infrastructure.response.ControllerResponse;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger("");

    @ExceptionHandler(value = { BusinessException.class })
    protected ResponseEntity handleBusinessException(final RuntimeException runtimeException, final WebRequest request) {
        logger.error("BUSINESS - " + runtimeException.getMessage());
        return handleExceptionInternal(runtimeException,
            new ControllerResponse<String>(runtimeException.getMessage()),
            new HttpHeaders(),
            HttpStatus.BAD_REQUEST,
            request);
    }

    @ExceptionHandler(value = { InternalErrorException.class })
    protected ResponseEntity handleInternalErrorException(final RuntimeException runtimeException, final WebRequest request) {
        logger.error(runtimeException.getMessage(), runtimeException);
        return handleExceptionInternal(runtimeException,
            new ControllerResponse<String>(runtimeException.getMessage()),
            new HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR,
            request);
    }
}
