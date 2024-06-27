package com.example.lectureservice.service.domain.response;

import com.example.lectureservice.entity.LectureEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LectureDetailResponse {
    private String lectureCode;
    private String title;
    private String name;
    private LocalDateTime date;
    private int maxParticipants;
    private int currParticipants;

    @Builder
    public LectureDetailResponse(String lectureCode, String title, String name, LocalDateTime date, int maxParticipants, int currParticipants) {
        this.lectureCode = lectureCode;
        this.title = title;
        this.name = name;
        this.date = date;
        this.maxParticipants = maxParticipants;
        this.currParticipants = currParticipants;
    }

    public static LectureDetailResponse of(LectureEntity entity) {
        return LectureDetailResponse.builder()
                .lectureCode(entity.getLectureCode())
                .title(entity.getTitle())
//                .name(entity.getName())
//                .maxParticipants(entity.getMaxParticipants())
                .build();
    }
}
