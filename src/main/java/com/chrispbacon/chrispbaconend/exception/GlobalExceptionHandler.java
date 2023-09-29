package com.chrispbacon.chrispbaconend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //https://www.baeldung.com/exception-handling-for-rest-with-spring

    @ExceptionHandler({IllegalInputException.class})
    protected ResponseEntity<Object> handleWrongInputException(IllegalInputException ex) {
        log.info(ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler({BadCredentialsException.class})
    protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        log.info(ex.getMessage());
        return ResponseEntity.status(401).body("Username and/or password is incorrect! Please, get your ducks in a row.");
    }

}
