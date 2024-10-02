package com.pos.crackers.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class BusinessException extends RuntimeException{

    public BusinessException(String reason){
        super(reason);
    }
}
