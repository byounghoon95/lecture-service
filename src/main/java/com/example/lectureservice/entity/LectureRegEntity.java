package com.example.lectureservice.entity;

import com.example.lectureservice.service.domain.response.LectureApplyResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "LECTURE_REG")
@Entity
public class LectureRegEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ONE TO ONE 일 때는 자동으로 unique 추가됨
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LECTURE_ID", referencedColumnName = "LECTURE_ID")
    private LectureDetailEntity detail;

    @Column(name = "USER_ID")
    private String userId;

    @Builder
    public LectureRegEntity(LectureDetailEntity detail, String userId) {
        this.detail = detail;
        this.userId = userId;
    }

    public LectureApplyResponse toDto() {
        return LectureApplyResponse.builder()
                .lectureCode(detail.getLecture().getLectureCode())
                .title(detail.getLecture().getTitle())
                .lectureId(id)
                .name(detail.getName())
                .date(detail.getDate())
                .maxParticipants(detail.getMaxParticipants())
                .currParticipants(detail.getCurrParticipants())
                .build()
                ;
    }
}