package com.candenizgumus.java14socialmedia.repository;

import com.candenizgumus.java14socialmedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository     extends JpaRepository<Post, Long>
{
}
