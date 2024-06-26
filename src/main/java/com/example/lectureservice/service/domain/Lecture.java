package com.example.lectureservice.service.domain;

import com.example.lectureservice.entity.LectureApplyHistoryEntity;

public class Lecture {
    String userId;
    String lectureCode;
    int maxParticipants;
    int currParticipants;

    public Lecture(String userId, String lectureCode) {
        this.userId = userId;
        this.lectureCode = lectureCode;
    }

    public Lecture(String lectureCode, int maxParticipants, int currParticipants) {
        this.lectureCode = lectureCode;
        this.maxParticipants = maxParticipants;
        this.currParticipants = currParticipants;
    }

    public String getUserId() {
        return userId;
    }

    public String getLectureCode() {
        return lectureCode;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public int getCurrParticipants() {
        return currParticipants;
    }

    public LectureApplyHistoryEntity toLectureApplyEntity() {
        return LectureApplyHistoryEntity.builder()
                .lectureCode(lectureCode)
                .userId(userId)
                .build();
    }
}
