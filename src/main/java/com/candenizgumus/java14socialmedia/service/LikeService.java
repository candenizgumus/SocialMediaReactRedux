package com.candenizgumus.java14socialmedia.service;

import com.candenizgumus.java14socialmedia.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService
{
        private final LikeRepository likeRepository;
}
