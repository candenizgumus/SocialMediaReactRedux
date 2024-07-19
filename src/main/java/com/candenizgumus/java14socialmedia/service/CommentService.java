package com.candenizgumus.java14socialmedia.service;

import com.candenizgumus.java14socialmedia.config.JwtManager;
import com.candenizgumus.java14socialmedia.dto.request.CommentSaveRequestDto;
import com.candenizgumus.java14socialmedia.dto.response.CommentListByPageResponseDto;
import com.candenizgumus.java14socialmedia.dto.response.CommentResponseDto;
import com.candenizgumus.java14socialmedia.dto.response.ResponseDto;
import com.candenizgumus.java14socialmedia.entity.Comment;
import com.candenizgumus.java14socialmedia.entity.Post;
import com.candenizgumus.java14socialmedia.exceptions.AuthException;
import com.candenizgumus.java14socialmedia.exceptions.ErrorType;
import com.candenizgumus.java14socialmedia.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Lazy
    @Autowired
    private PostService postService;


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

    public List<CommentResponseDto> getCommentsByPostId(Long postId, int page, int size) {

        Page<Comment> comments = commentRepository.findByPostId(postId, PageRequest.of(page, size));


        List<CommentResponseDto> result = new ArrayList<>();

        comments.forEach(u -> {
            result.add(CommentResponseDto
                    .builder()
                    .sharedDate(u.getDate())
                    .comment(u.getComment())
                    .username(userService.findById(u.getUserId()).getUserName())
                    .postId(u.getPostId())
                    .avatar(userService.findById(u.getUserId()).getAvatar())
                    .build());
        });
        System.out.println(result);

        return result ;
    }

    public List<CommentResponseDto> getCommentListByPost(CommentListByPageResponseDto dto)
    {

        List<CommentResponseDto> result = getCommentsByPostId(dto.getPostId(), dto.getPage(), dto.getSize());
        return result;
    }
}
