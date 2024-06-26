package com.example.lectureservice.repository.lectureApplyHistory;

import com.example.lectureservice.entity.LectureApplyHistoryEntity;

import java.util.Optional;

public interface LectureApplyHistoryRepository {
    LectureApplyHistoryEntity save(LectureApplyHistoryEntity entity);
    Optional<LectureApplyHistoryEntity> findByUserIdAndLectureCode(String userId, String lectureCode);
    void deleteAllInBatch();
}