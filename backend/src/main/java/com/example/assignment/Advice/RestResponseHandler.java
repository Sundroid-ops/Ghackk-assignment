package com.example.assignment.Advice;

import com.example.assignment.Errors.ClientNotFoundError;
import com.example.assignment.Errors.InternalServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseHandler {
    private static final Logger logs = LoggerFactory.getLogger(RestResponseHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidRequests(MethodArgumentNotValidException exception){
        Map<String, String> exceptions = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(err -> exceptions.put(err.getField(), err.getDefaultMessage()));
        logs.warn("Invalid user request");
        return ResponseEntity.badRequest().body(exceptions);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<ErrorMessage> internalServerError(InternalServerError error){
        ErrorMessage err = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, error.getMessage());
        logs.warn("Internal server error : {}", error.getMessage());
        return ResponseEntity.internalServerError().body(err);
    }

    @ExceptionHandler(ClientNotFoundError.class)
    public ResponseEntity<ErrorMessage> dataNotFound(ClientNotFoundError error){
        ErrorMessage err = new ErrorMessage(HttpStatus.NOT_FOUND, error.getMessage());
        logs.info("Client Not Found Error");
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
