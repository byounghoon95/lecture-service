package com.example.lectureservice.repository.lecture;

import com.example.lectureservice.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LectureJpaRepository extends JpaRepository<LectureEntity,Long> {
    @Query("SELECT l FROM LectureEntity l " +
            "LEFT JOIN FETCH l.details d " +
            "WHERE d.date > :date AND d.currParticipants < 30"
    )
    List<LectureEntity> findLecturesAfterDate(@Param("date") LocalDateTime date);
    @Query("SELECT l FROM LectureEntity l LEFT JOIN FETCH l.details s WHERE l.lectureCode = :lectureCode AND s.id = :lectureDetailId")
    Optional<LectureEntity> findByLectureCodeAndId(@Param("lectureCode") String lectureCode, @Param("lectureDetailId") Long lectureDetailId);
    @Query("SELECT l FROM LectureEntity l LEFT JOIN FETCH l.details s WHERE l.lectureCode = :lectureCode")
    Optional<LectureEntity> findLectureDetail(@Param("lectureCode") String lectureCode);
}

