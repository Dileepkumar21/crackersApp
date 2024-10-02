package com.pos.crackers.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class ApiStatus {

    private Integer code;
    private String reason;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String response) {
        this.reason = response;
    }

    public ApiStatus(Integer code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public ApiStatus() {
    }
}
