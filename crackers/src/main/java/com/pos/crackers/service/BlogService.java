package com.pos.crackers.service;

import com.pos.crackers.entities.BlogItemRequest;
import com.pos.crackers.model.BlogItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogService {

    @Autowired
    PersistenceService persistenceService;

    public Long persistBlogItem(BlogItemRequest blogItemRequest){
        BlogItem blogItem = new BlogItem();
        blogItem.setContent(blogItemRequest.getContent());
        blogItem.setTitle(blogItemRequest.getTitle());
        return persistenceService.persistBlogItem(blogItem).getItemId();
    }

    public List<BlogItem> getBlogList() {
        return persistenceService.getAllBLogs();
    }
}
