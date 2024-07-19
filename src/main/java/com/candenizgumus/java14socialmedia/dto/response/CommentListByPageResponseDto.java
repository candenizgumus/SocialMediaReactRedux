package com.candenizgumus.java14socialmedia.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommentListByPageResponseDto
{
    Long postId;
    Integer page;
    Integer size;
}
