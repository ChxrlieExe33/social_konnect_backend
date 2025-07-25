package com.cdcrane.social_konnect_backend.likes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Like, UUID> {

    int countByPostId(UUID postId);

    boolean existsByPostIdAndUserId(UUID postId, long userId);

    Optional<Like> findByPostIdAndUserId(UUID postId, long userId);

    @Query("SELECT l.user.username FROM Like l WHERE l.post.id = ?1")
    Page<String> getUsernamesWhoLikePost(UUID postId, Pageable pageable);

}
