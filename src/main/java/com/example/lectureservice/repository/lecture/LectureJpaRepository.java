package com.example.lectureservice.repository.lecture;

import com.example.lectureservice.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LectureJpaRepository extends JpaRepository<LectureEntity,Long> {
    Optional<LectureEntity> findByLectureCode(String lectureCode);
    @Query("SELECT l FROM LectureCountEntity l")
    List<LectureEntity> findLecturesAfterDate(LocalDateTime lectureId);
}