package com.yaochen.user_posts_app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yaochen.user_posts_app.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
