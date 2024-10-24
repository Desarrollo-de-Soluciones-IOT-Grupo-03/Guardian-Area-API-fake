package com.digitaldart.guardian.area.shared.domain.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}