package com.candenizgumus.java14socialmedia.service;

import com.candenizgumus.java14socialmedia.repository.FollowRepository;
import com.candenizgumus.java14socialmedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService
{
    private final FollowRepository followRepository;
}
