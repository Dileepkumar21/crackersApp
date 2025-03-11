package com.pos.crackers.model;

import jakarta.persistence.*;

@Entity
@Table(name = "BLOG_ITEM",schema = "BLOGAPP")
public class BlogItem {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    Long itemId;

    @Column(name = "title")
    String title;

    @Column(name = "content")
    String content;

    public Long getItemId() {
        return itemId;
    }

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
}
