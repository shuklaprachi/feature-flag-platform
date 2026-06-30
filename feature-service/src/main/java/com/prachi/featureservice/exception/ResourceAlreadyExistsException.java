package com.prachi.featureservice.exception;

public class ResourceAlreadyExistsException 
        extends RuntimeException {


    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

}