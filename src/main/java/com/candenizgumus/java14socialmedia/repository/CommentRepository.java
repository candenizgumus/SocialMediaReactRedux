package com.candenizgumus.java14socialmedia.repository;

import com.candenizgumus.java14socialmedia.entity.Comment;
import com.candenizgumus.java14socialmedia.entity.User;
import com.candenizgumus.java14socialmedia.views.VwUserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>
{
    Page<Comment> findByPostId(Long postId, Pageable pageable);

    @Query(value = "SELECT * FROM tblcomment WHERE post_id = ?1 ORDER BY date DESC LIMIT ?2", nativeQuery = true)
    List<Comment> findCommentsByPostId(Long postId, int size);


}
