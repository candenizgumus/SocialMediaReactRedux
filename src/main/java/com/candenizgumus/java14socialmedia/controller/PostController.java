package com.candenizgumus.java14socialmedia.controller;

import com.candenizgumus.java14socialmedia.dto.request.CreatePostRequestDto;
import com.candenizgumus.java14socialmedia.dto.response.PostListResponseDto;
import com.candenizgumus.java14socialmedia.dto.response.ResponseDto;
import com.candenizgumus.java14socialmedia.service.CommentService;
import com.candenizgumus.java14socialmedia.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController
{
    private final PostService postService;

    @PostMapping("/create-post")
    @CrossOrigin("*")
    public ResponseEntity<ResponseDto<Boolean>> createPost(@RequestBody CreatePostRequestDto dto)
    {
        postService.createPost(dto);
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                        .data(true)
                        .code(200)
                        .message("Post olusturuldu")
                .build());
    }

    @GetMapping("/get-post-list")
    @CrossOrigin("*")
    public ResponseEntity<ResponseDto<List<PostListResponseDto>>> getPostList(String token)
    {

        return ResponseEntity.ok(ResponseDto.<List<PostListResponseDto>>builder()
                        .data(postService.getPostList(token))
                        .code(200)
                        .message("Postlist getirildi.")
                .build());
    }
}
