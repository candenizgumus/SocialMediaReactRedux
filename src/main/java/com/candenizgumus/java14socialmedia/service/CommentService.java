package com.candenizgumus.java14socialmedia.service;

import com.candenizgumus.java14socialmedia.config.JwtManager;
import com.candenizgumus.java14socialmedia.dto.request.CommentSaveRequestDto;
import com.candenizgumus.java14socialmedia.dto.response.CommentResponseDto;
import com.candenizgumus.java14socialmedia.dto.response.ResponseDto;
import com.candenizgumus.java14socialmedia.entity.Comment;
import com.candenizgumus.java14socialmedia.entity.Post;
import com.candenizgumus.java14socialmedia.exceptions.AuthException;
import com.candenizgumus.java14socialmedia.exceptions.ErrorType;
import com.candenizgumus.java14socialmedia.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService
{
    private final CommentRepository commentRepository;
    private final JwtManager jwtManager;
    private final UserService userService;
    private final PostService postService;

    public String save(CommentSaveRequestDto dto)
    {
        Optional<Long> userId = jwtManager.getAuthId(dto.getToken());
        if (userId.isEmpty())
            throw new AuthException(ErrorType.INVALID_TOKEN);

        commentRepository.save(Comment
                .builder()
                        .postId(dto.getPostId())
                        .userId(userId.get())
                        .comment(dto.getComment())
                        .date(System.currentTimeMillis())
                .build());

        postService.updateCommentCount(dto.getPostId());
     return "Yorum kaydedildi";
    }

    public List<CommentResponseDto> getCommentList()
    {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentResponseDto> result = new ArrayList<>();

        commentList.forEach(u -> {
            result.add(CommentResponseDto
                    .builder()
                    .sharedDate(u.getDate())
                    .comment(u.getComment())
                    .username(userService.findById(u.getUserId()).getUserName())
                    .postId(u.getPostId())
                    .avatar(userService.findById(u.getUserId()).getAvatar())
                    .build());


        });


        return result.reversed();

    }
}
