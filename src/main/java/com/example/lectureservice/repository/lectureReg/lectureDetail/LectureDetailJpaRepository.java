package com.example.lectureservice.repository.lectureReg.lectureDetail;

import com.example.lectureservice.entity.LectureDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureDetailJpaRepository extends JpaRepository<LectureDetailEntity,Long> {
}