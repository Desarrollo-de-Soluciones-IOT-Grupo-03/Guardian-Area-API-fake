package com.digitaldart.guardian.area.shared.domain.exceptions;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(){
        super();
    }

    public UnauthorizedException(String message){
        super(message);
    }
}