package com.candenizgumus.java14socialmedia.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserSaveRequestDto
{
    String userName;
    String password;
    String rePassword;
    String email;
}
