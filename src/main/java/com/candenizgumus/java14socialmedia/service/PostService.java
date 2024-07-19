package com.candenizgumus.java14socialmedia.service;

import com.candenizgumus.java14socialmedia.config.JwtManager;
import com.candenizgumus.java14socialmedia.dto.request.CreatePostRequestDto;
import com.candenizgumus.java14socialmedia.dto.response.PostListResponseDto;
import com.candenizgumus.java14socialmedia.dto.response.ResponseDto;
import com.candenizgumus.java14socialmedia.entity.Post;
import com.candenizgumus.java14socialmedia.entity.User;
import com.candenizgumus.java14socialmedia.exceptions.AuthException;
import com.candenizgumus.java14socialmedia.exceptions.ErrorType;
import com.candenizgumus.java14socialmedia.repository.PostRepository;
import com.candenizgumus.java14socialmedia.views.VwUserAvatar;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService
{
    private final PostRepository postRepository;
    private final JwtManager    jwtManager;
    private final UserService userService;

    public void createPost(CreatePostRequestDto dto)
    {
        Optional<Long> userId = jwtManager.getAuthId(dto.getToken());
        if (userId.isEmpty())
            throw new AuthException(ErrorType.INVALID_TOKEN);

        postRepository.save(Post.builder()
                        .comment(dto.getComment())
                        .commentCount(0L)
                        .likeCount(0L)
                        .photo(dto.getUrl())
                        .sharedDate(System.currentTimeMillis())
                        .userId(userId.get())
                .build());
    }


    public List<PostListResponseDto> getPostList(String token)
    {
        Optional<Long> userId = jwtManager.getAuthId(token);
        if (userId.isEmpty())
            throw new AuthException(ErrorType.INVALID_TOKEN);

        List<Post> postList = postRepository.findAll();
        List<PostListResponseDto> result = new ArrayList<>();
        List<Long> userIds = postList.stream().map(Post::getUserId).toList();
        Map<Long, User> mapUserList = userService.findAllByIdsMap(userIds);
        //List<VwUserAvatar> userAvatarList = userService.getUserAvatarList(); // 20K+
        postList.forEach(p->{
            //VwUserAvatar userAvatar = userAvatarList.stream().filter(u -> u.getId().equals(p.getUserId())).findFirst().get();
            result.add(PostListResponseDto
                    .builder()
                    .postId(p.getId())
                    .avatar(mapUserList.get(p.getUserId()).getAvatar())
                    .comment(p.getComment())
                    .commentCount(p.getCommentCount())
                    .likeCount(p.getLikeCount())
                    .photo(p.getPhoto())
                    .sharedDate(p.getSharedDate())
                    .userId(p.getUserId())
                    .userName(mapUserList.get(p.getUserId()).getUserName())
                    .build());
        });


        return result.stream().sorted(Comparator.comparingLong(PostListResponseDto::getSharedDate).reversed()).toList();
    }

    public void updateCommentCount(Long postId)
    {
        postRepository.findById(postId).ifPresent(p -> {
            p.setCommentCount(p.getCommentCount() + 1);
            postRepository.save(p);
        });
    }
}
