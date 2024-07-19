package com.candenizgumus.java14socialmedia.repository;

import com.candenizgumus.java14socialmedia.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository     extends JpaRepository<Like, Long>
{
}
