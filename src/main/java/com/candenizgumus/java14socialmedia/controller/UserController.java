package com.candenizgumus.java14socialmedia.controller;

import com.candenizgumus.java14socialmedia.config.JwtManager;
import com.candenizgumus.java14socialmedia.dto.request.UserLoginRequestDto;
import com.candenizgumus.java14socialmedia.dto.request.UserSaveRequestDto;
import com.candenizgumus.java14socialmedia.dto.response.ResponseDto;
import com.candenizgumus.java14socialmedia.dto.response.SearchUserResponseDto;
import com.candenizgumus.java14socialmedia.entity.User;
import com.candenizgumus.java14socialmedia.exceptions.AuthException;
import com.candenizgumus.java14socialmedia.exceptions.ErrorType;
import com.candenizgumus.java14socialmedia.service.UserService;
import com.candenizgumus.java14socialmedia.views.VwUserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;
    private final JwtManager jwtManager;

    @PostMapping("/register")
    @CrossOrigin("*")
    public ResponseEntity<ResponseDto<Boolean>> register(@RequestBody UserSaveRequestDto dto){
        userService.save(dto);
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                        .code(200)
                        .message("Kayıt yapıldı")
                        .data(true)
                .build());
    }

    @PostMapping("/login")
    @CrossOrigin("*")
    public ResponseEntity<ResponseDto<String>> login(@RequestBody UserLoginRequestDto dto){

        Optional<User> user = userService.login(dto);
        if(user.isEmpty())
            throw new AuthException(ErrorType.BAD_REQUEST_USERNAME_OR_PASSWORD_ERROR);
        String token = jwtManager.createToken(user.get().getId());
        return ResponseEntity.ok(ResponseDto.<String>builder()
                .code(200)
                .message("Başarılı şekilde giriş yapıldı")
                .data(token)
                .build());
    }


    @GetMapping("/search")
    @CrossOrigin("*")
    public ResponseEntity<List<SearchUserResponseDto>> getUserList(String userName){
        return ResponseEntity.ok(userService.search(userName));
    }

    @GetMapping("/get-profile")
    @CrossOrigin("*")
    public ResponseEntity<ResponseDto<VwUserProfile>> getProfile(String token){
        return ResponseEntity.ok(ResponseDto.<VwUserProfile>builder()
                .data(userService.getProfileByToken(token))
                .code(200)
                .message("Profil bilgileri getirildi")
                .build());
    }

    @PostMapping("/edit-profile")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> editProfile(@RequestBody User user){
        userService.editProfile(user);
        return ResponseEntity.ok(true);

    }
}
