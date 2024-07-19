package com.candenizgumus.java14socialmedia.controller;

import com.candenizgumus.java14socialmedia.service.CommentService;
import com.candenizgumus.java14socialmedia.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/follow")
@RestController
public class FollowController
{
    private final FollowService followService;
}
