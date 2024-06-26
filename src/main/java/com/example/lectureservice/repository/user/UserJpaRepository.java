package com.example.lectureservice.repository.user;

import com.example.lectureservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUserId(String userId);
    void deleteAll();
}
