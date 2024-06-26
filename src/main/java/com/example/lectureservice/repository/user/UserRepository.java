package com.example.lectureservice.repository.user;

import com.example.lectureservice.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findByUserId(String userId);
    UserEntity save(UserEntity entity);
    void deleteAllInBatch();
}
