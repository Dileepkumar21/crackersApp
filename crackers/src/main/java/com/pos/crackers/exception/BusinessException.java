package com.pos.crackers.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String reason){
        super(reason);
    }
}
