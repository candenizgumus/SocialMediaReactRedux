package com.candenizgumus.java14socialmedia.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommentResponseDto
{
    Long postId;
    String username;
    String avatar;
    String comment;
    Long sharedDate;
}
