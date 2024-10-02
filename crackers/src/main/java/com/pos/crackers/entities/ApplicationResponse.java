package com.pos.crackers.entities;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class ApplicationResponse {

    private String body;

    public ApplicationResponse(String body) {
        this.body = body;
    }
}
