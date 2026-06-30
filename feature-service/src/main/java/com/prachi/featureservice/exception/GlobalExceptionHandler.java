package com.prachi.featureservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String,Object> handleAlreadyExists(
            ResourceAlreadyExistsException ex
    ){

        return Map.of(
                "message", ex.getMessage(),
                "timestamp", Instant.now()
        );

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> handleNotFound(
            ResourceNotFoundException ex
    ){

        return Map.of(
                "message", ex.getMessage(),
                "timestamp", Instant.now()
        );

    }

}