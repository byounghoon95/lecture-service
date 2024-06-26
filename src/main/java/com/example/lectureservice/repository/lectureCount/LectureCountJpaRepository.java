package com.example.lectureservice.repository.lectureCount;

import com.example.lectureservice.entity.LectureCountEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LectureCountJpaRepository extends JpaRepository<LectureCountEntity, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT lc FROM LectureCountEntity lc WHERE lc.lectureCode = :lectureCode")
    Optional<LectureCountEntity> findByLectureCodeForUpdate(@Param("lectureCode") String lectureCode);
}
