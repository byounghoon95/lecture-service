package com.example.lectureservice.repository.lectureReg.lectureDetail;

import com.example.lectureservice.entity.LectureDetailEntity;

import java.util.Optional;

public interface LectureDetailRepository {
    Optional<LectureDetailEntity> findById(Long id);
    void deleteAllInBatch();
    LectureDetailEntity save(LectureDetailEntity entity);
    void flush();
}