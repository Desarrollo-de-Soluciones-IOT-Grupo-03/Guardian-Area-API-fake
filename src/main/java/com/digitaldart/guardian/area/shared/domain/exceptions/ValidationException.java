package com.digitaldart.guardian.area.shared.domain.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException() {
        super();
    }

    public ValidationException(String message){
        super(message);
    }

}