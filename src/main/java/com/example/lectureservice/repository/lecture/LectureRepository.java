package com.example.lectureservice.repository.lecture;

import com.example.lectureservice.entity.LectureEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LectureRepository {
    Optional<LectureEntity> findByLectureCode(String lectureCode);
    LectureEntity save(LectureEntity entity);
    List<LectureEntity> findLecturesAfterDate(LocalDateTime now);
    void deleteAllInBatch();
}