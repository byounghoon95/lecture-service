package com.example.lectureservice.repository.user;

import com.example.lectureservice.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userRepository;

    @Override
    public Optional<UserEntity> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public UserEntity save(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    public void deleteAllInBatch() {
        userRepository.deleteAllInBatch();
    }
}
