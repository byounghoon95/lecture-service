package com.example.lectureservice.service.domain;

import com.example.lectureservice.entity.LectureApplyHistoryEntity;
import com.example.lectureservice.entity.LectureDetailEntity;
import com.example.lectureservice.entity.LectureEntity;
import com.example.lectureservice.enums.Status;

public class Lecture {
    String userId;
    String lectureCode;
    Status status;
    int maxParticipants;
    int currParticipants;
    Long lectureDetailId;
    public Lecture(String userId, String lectureCode) {
        this.userId = userId;
        this.lectureCode = lectureCode;
    }

    public Lecture(String userId, String lectureCode, Long lectureDetailId) {
        this.userId = userId;
        this.lectureCode = lectureCode;
        this.lectureDetailId = lectureDetailId;
    }

    public Lecture(String userId, String lectureCode, Status status) {
        this.userId = userId;
        this.lectureCode = lectureCode;
        this.status = status;
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

    public Long getLectureDetailId() {
        return lectureDetailId;
    }

    public void updateCurrParticipants(int currParticipants) {
        this.currParticipants = currParticipants;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public Lecture of(LectureEntity entity) {
        LectureDetailEntity detail = entity.getDetails().get(0);
        return new Lecture(lectureCode, maxParticipants, currParticipants);
    }

    public LectureApplyHistoryEntity toLectureApplyEntity() {
        return LectureApplyHistoryEntity.builder()
                .lectureCode(lectureCode)
                .userId(userId)
                .status(status)
                .build();
    }
}
