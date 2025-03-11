package com.pos.crackers.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Getter
@Setter
@Component
@ToString
public class BlogItemRequest {

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BlogItemRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public BlogItemRequest() {
    }
}
