package com.example.lectureservice.repository.lectureApplyHistory;

import com.example.lectureservice.entity.LectureApplyHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureApplyHistoryJpaRepository extends JpaRepository<LectureApplyHistoryEntity, Long> {
    Optional<LectureApplyHistoryEntity> findByUserIdAndLectureCode(String userId, String lectureCode);
}