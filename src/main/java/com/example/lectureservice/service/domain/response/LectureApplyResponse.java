package com.example.lectureservice.service.domain.response;

import com.example.lectureservice.entity.LectureDetailEntity;
import com.example.lectureservice.entity.LectureEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LectureApplyResponse {
    private Long lectureId;
    private String lectureCode;
    private String title;
    private String name;
    private LocalDateTime date;
    private int maxParticipants;
    private int currParticipants;

    @Builder
    public LectureApplyResponse(Long lectureId, String lectureCode, String title, String name, LocalDateTime date, int maxParticipants, int currParticipants) {
        this.lectureId = lectureId;
        this.lectureCode = lectureCode;
        this.title = title;
        this.name = name;
        this.date = date;
        this.maxParticipants = maxParticipants;
        this.currParticipants = currParticipants;
    }

    /**
     * 코드와 날짜 조회로 detail 의 리턴값은 무조건 한개
     * */
    public static LectureApplyResponse of(LectureEntity entity) {
        LectureDetailEntity detail = entity.getDetails().get(0);
        return LectureApplyResponse.builder()
                .lectureId(entity.getId())
                .lectureCode(entity.getLectureCode())
                .title(entity.getTitle())
                .name(detail.getName())
                .date(detail.getDate())
                .maxParticipants(detail.getMaxParticipants())
                .currParticipants(detail.getCurrParticipants())
                .build();
    }
}
