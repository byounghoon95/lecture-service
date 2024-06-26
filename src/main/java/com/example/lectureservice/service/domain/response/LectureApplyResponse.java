package com.example.lectureservice.service.domain.response;

import com.example.lectureservice.entity.LectureEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LectureApplyResponse {
    private String lectureCode;
    private String title;
    private LocalDateTime date;
    private int maxParticipants;
    private int currParticipants;

    @Builder
    public LectureApplyResponse(String lectureCode, String title, LocalDateTime date, int maxParticipants, int currParticipants) {
        this.lectureCode = lectureCode;
        this.title = title;
        this.date = date;
        this.maxParticipants = maxParticipants;
        this.currParticipants = currParticipants;
    }

    public static LectureApplyResponse of(LectureEntity entity) {
        return LectureApplyResponse.builder()
                .lectureCode(entity.getLectureCode())
                .title(entity.getTitle())
                .maxParticipants(entity.getMaxParticipants())
                .build();
    }
}
