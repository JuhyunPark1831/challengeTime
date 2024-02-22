package com.sideProject.challengeTime.domain.user.repository;

import com.sideProject.challengeTime.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
