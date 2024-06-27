package com.example.lectureservice.repository.lecture;

import com.example.lectureservice.entity.LectureEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LectureRepository {
    LectureEntity save(LectureEntity entity);
    List<LectureEntity> findLecturesAfterDate(LocalDateTime now);
    void deleteAllInBatch();
    Optional<LectureEntity> findByLectureCodeAndId(String lectureCode, Long lectureDetailId);

    Optional<LectureEntity> findLectureDetail(String lectureCode);
}