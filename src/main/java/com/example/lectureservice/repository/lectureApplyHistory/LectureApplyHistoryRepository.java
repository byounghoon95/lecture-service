package com.example.lectureservice.repository.lectureApplyHistory;

import com.example.lectureservice.entity.LectureApplyHistoryEntity;

public interface LectureApplyHistoryRepository {
    LectureApplyHistoryEntity save(LectureApplyHistoryEntity entity);
    void deleteAllInBatch();
}