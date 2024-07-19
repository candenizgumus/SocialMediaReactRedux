package com.candenizgumus.java14socialmedia.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwUserAvatar
{
    Long id;
    String userName;
    String avatar;
}
