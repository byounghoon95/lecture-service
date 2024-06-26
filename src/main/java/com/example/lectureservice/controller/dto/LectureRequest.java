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

    @Builder
    public LectureRequest(String lectureCode, String userId) {
        this.lectureCode = lectureCode;
        this.userId = userId;
    }

    public Lecture toDomain() {
        return new Lecture(userId,lectureCode);
    }

}
