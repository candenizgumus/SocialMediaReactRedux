package com.candenizgumus.java14socialmedia.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllServices
{
    @Autowired
    private final CommentService commentService;

    public AllServices(CommentService commentService)
    {
        this.commentService = commentService;
    }
}
