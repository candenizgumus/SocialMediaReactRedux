package com.candenizgumus.java14socialmedia.repository;

import com.candenizgumus.java14socialmedia.entity.Comment;
import com.candenizgumus.java14socialmedia.entity.User;
import com.candenizgumus.java14socialmedia.views.VwUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>
{


}
