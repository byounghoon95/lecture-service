package com.example.lectureservice.repository.lectureCount;

import com.example.lectureservice.entity.LectureCountEntity;

import java.util.Optional;

public interface LectureCountRepository {
    Optional<LectureCountEntity> findByLectureCodeForUpdate(String lectureCode);
    LectureCountEntity save(LectureCountEntity entity);
    void flush();
    void deleteAllInBatch();
}
