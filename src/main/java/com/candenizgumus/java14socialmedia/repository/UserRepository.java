package com.candenizgumus.java14socialmedia.repository;

import com.candenizgumus.java14socialmedia.entity.User;
import com.candenizgumus.java14socialmedia.views.VwUserAvatar;
import com.candenizgumus.java14socialmedia.views.VwUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findOptionalByUserNameAndPassword(String userName, String password);

    List<User> findAllByUserNameContainingIgnoreCase(String userName);

    @Query("SELECT new com.candenizgumus.java14socialmedia.views.VwUserProfile(u.name,u.userName,u.avatar,u.followerCount,u.followingCount,u.about,u.bornDate,u.phone,u.address) FROM User u WHERE u.id =?1")
    VwUserProfile getByAuthId(Long id);

    @Query("SELECT new com.candenizgumus.java14socialmedia.views.VwUserAvatar(u.id,u.userName,u.avatar) FROM User u WHERE u.id =?1")
    VwUserAvatar getUserAvatar(Long id);

    @Query("SELECT new com.candenizgumus.java14socialmedia.views.VwUserAvatar(u.id,u.userName,u.avatar) FROM User u ")
    List<VwUserAvatar> getUserAvatarList();

}
