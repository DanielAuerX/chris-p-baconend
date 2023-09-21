package com.chrispbacon.chrispbaconend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

        //https://www.baeldung.com/exception-handling-for-rest-with-spring

//        @ExceptionHandler({RuntimeException.class})
//        protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
//            log.info("Following response was sent: " + ex.getMessage());
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        }
        @ExceptionHandler({WrongIdException.class})
        protected ResponseEntity<Object> handleWrongIdException(WrongIdException ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }


        //@ExceptionHandler({MethodArgumentNotValidException.class})
        //public ResponseEntity<Object> handleNullArguments(MethodArgumentNotValidException ex) {
        //    log.error(ex.getMessage());
        //    return ResponseEntity.status(400).body("You have to enter the right parameters!");
        //}

}
