package com.example.lectureservice.repository.lectureDetail;

import com.example.lectureservice.entity.LectureRegEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LectureRegJpaRepository extends JpaRepository<LectureRegEntity,Long> {
    @Query("SELECT l FROM LectureRegEntity l WHERE l.detail.id = :lectureId AND l.userId = :userId")
    Optional<LectureRegEntity> existsByLectureIdAndUserId(@Param("lectureId") Long lectureId, @Param("userId") String userId);

    @Query("SELECT l FROM LectureRegEntity l LEFT JOIN FETCH l.detail s WHERE l.userId = :userId")
    List<LectureRegEntity> findByUserId(@Param("userId") String userId);
}