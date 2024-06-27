package com.example.lectureservice.controller.dto;

import com.example.lectureservice.service.domain.Lecture;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LectureRequest {
    String lectureCode;
    String userId;
    Long lectureDetailId;

    @Builder
    public LectureRequest(String lectureCode, String userId, Long lectureDetailId) {
        this.lectureCode = lectureCode;
        this.userId = userId;
        this.lectureDetailId = lectureDetailId;
    }

    public Lecture toDomain() {
        return new Lecture(userId,lectureCode,lectureDetailId);
    }
}
