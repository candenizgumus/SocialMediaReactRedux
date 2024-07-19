package com.candenizgumus.java14socialmedia.entity;

import com.candenizgumus.java14socialmedia.utility.FollowState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblfollow")
public class Follow
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    /**
     * takip etme isteği gönderen kişi
     */
    Long userId;
    /**
     * takip edilen kişi
     */
    Long followId;

    @Enumerated(EnumType.STRING)
    FollowState state;
}
