package com.sideProject.challengeTime.domain.user.repository;

import com.sideProject.challengeTime.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = ?1")
    public Optional<User> findByEmail(String email);
}
