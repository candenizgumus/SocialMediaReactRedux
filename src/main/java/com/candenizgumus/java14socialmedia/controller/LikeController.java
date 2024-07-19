package com.candenizgumus.java14socialmedia.controller;

import com.candenizgumus.java14socialmedia.service.CommentService;
import com.candenizgumus.java14socialmedia.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/like")
@RestController
public class LikeController
{
    private final LikeService likeService;
}
