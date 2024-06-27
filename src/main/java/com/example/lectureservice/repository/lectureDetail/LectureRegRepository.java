package com.example.lectureservice.repository.lectureDetail;

import com.example.lectureservice.entity.LectureRegEntity;
import com.example.lectureservice.service.domain.response.LectureResponse;

import java.util.List;
import java.util.Optional;

public interface LectureRegRepository {
    void deleteAllInBatch();
    Optional<LectureRegEntity> isReg(Long lectureId, String userId);
    LectureRegEntity save(LectureRegEntity entity);
    List<LectureRegEntity> findByUserId(String userId);
}