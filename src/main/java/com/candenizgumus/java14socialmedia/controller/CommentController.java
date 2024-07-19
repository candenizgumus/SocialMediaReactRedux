package com.candenizgumus.java14socialmedia.controller;

import com.candenizgumus.java14socialmedia.dto.request.CommentSaveRequestDto;
import com.candenizgumus.java14socialmedia.dto.response.CommentResponseDto;
import com.candenizgumus.java14socialmedia.dto.response.PostListResponseDto;
import com.candenizgumus.java14socialmedia.dto.response.ResponseDto;
import com.candenizgumus.java14socialmedia.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentController
{
    private final CommentService commentService;

    @PostMapping("/save")
    @CrossOrigin("*")
    public ResponseEntity<String> getCommentList(@RequestBody CommentSaveRequestDto dto)
    {
        return ResponseEntity.ok(commentService.save(dto));
    }

    @GetMapping("/get-comment-list")
    @CrossOrigin("*")
    public ResponseEntity<ResponseDto<List<CommentResponseDto>>> getCommentList()
    {

        return ResponseEntity.ok(ResponseDto.<List<CommentResponseDto>>builder()
                .data(commentService.getCommentList())
                .code(200)
                .message("Comments getirildi.")
                .build());
    }
}
