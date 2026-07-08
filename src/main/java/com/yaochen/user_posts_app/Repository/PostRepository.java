package com.yaochen.user_posts_app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yaochen.user_posts_app.Entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
}
