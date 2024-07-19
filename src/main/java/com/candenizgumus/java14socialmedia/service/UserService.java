package com.candenizgumus.java14socialmedia.service;

import com.candenizgumus.java14socialmedia.config.JwtManager;
import com.candenizgumus.java14socialmedia.dto.request.UserLoginRequestDto;
import com.candenizgumus.java14socialmedia.dto.request.UserSaveRequestDto;
import com.candenizgumus.java14socialmedia.dto.response.SearchUserResponseDto;
import com.candenizgumus.java14socialmedia.entity.User;
import com.candenizgumus.java14socialmedia.exceptions.AuthException;
import com.candenizgumus.java14socialmedia.exceptions.ErrorType;
import com.candenizgumus.java14socialmedia.repository.UserRepository;
import com.candenizgumus.java14socialmedia.views.VwUserAvatar;
import com.candenizgumus.java14socialmedia.views.VwUserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final JwtManager jwtManager;

    public User save(UserSaveRequestDto dto)
    {
        return userRepository.save(User
                .builder()
                        .userName(dto.getUserName())
                        .password(dto.getPassword())
                        .email(dto.getEmail())

                .build());
    }


    public Optional<User> login(UserLoginRequestDto dto)
    {
         return userRepository.findOptionalByUserNameAndPassword(dto.getUserName(), dto.getPassword());

    }

    public List<SearchUserResponseDto> search(String userName)
    {
        List<User> userList;
        List<SearchUserResponseDto> result = new ArrayList<>();
        if (Objects.isNull(userName))
        {
            userList = userRepository.findAll();
        }
        else
        {
            userList = userRepository.findAllByUserNameContainingIgnoreCase(userName);
        }

        userList.forEach(user -> {
            result.add(SearchUserResponseDto
                    .builder()
                    .avatar(user.getAvatar())
                    .userName(user.getUserName())
                            .email(user.getEmail())
                    .build());
        });

        return result;
    }

    public VwUserProfile getProfileByToken(String token)
    {
        Optional<Long> authId = jwtManager.getAuthId(token);
        if (authId.isEmpty())
            throw new AuthException(ErrorType.INVALID_TOKEN);
        return userRepository
                .getByAuthId(authId.get());

    }

    public void editProfile(User user)
    {
        userRepository.save(user);
    }


    public VwUserAvatar getUserAvatar(Long id)
    {
        return userRepository.getUserAvatar(id);
    }

    public List<VwUserAvatar> getUserAvatarList()
    {
        return userRepository.getUserAvatarList();
    }

    public List<User> findAllByIds(List<Long> userIds){
        return userRepository.findAllById(userIds);
    }

    public Map<Long, User> findAllByIdsMap(List<Long> userIds){
        List<User> userList = userRepository.findAllById(userIds);
        Map<Long, User> result = userList.stream().collect(
                Collectors.toMap(User::getId, u -> u)
        );
        return result;
    }

    public User findById(Long id){

        return userRepository.findById(id).get();
    }

}
