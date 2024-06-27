package com.example.lectureservice.entity;

import com.example.lectureservice.service.domain.response.LectureDetailsResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Table(name = "LECTURE_DETAIL")
@Entity
public class LectureDetailEntity extends BaseEntity {
    @Id
    @Column(name = "LECTURE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LECTURE_CODE", referencedColumnName = "LECTURE_CODE")
    private LectureEntity lecture;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @Column(nullable = false, name = "DATE")
    private LocalDateTime date;

    @Column(nullable = false, name = "NAME")
    private String name;

    @Column(nullable = false, name = "MAX_PARTICIPANTS")
    private int maxParticipants;

    @Column(nullable = false, name = "CURR_PARTICIPANTS")
    private int currParticipants;

    @Builder
    public LectureDetailEntity(LectureEntity lecture, LocalDateTime date, String name, int maxParticipants, int currParticipants) {
        this.lecture = lecture;
        this.date = date;
        this.name = name;
        this.maxParticipants = maxParticipants;
        this.currParticipants = currParticipants;
    }

    public LectureDetailsResponse toDto() {
        return LectureDetailsResponse.builder()
                .date(date)
                .name(name)
                .maxParticipants(maxParticipants)
                .currParticipants(currParticipants)
                .build();
    }

    public void addCurrParticipants() {
        currParticipants++;
    }

    public void minusCurrParticipants() {
        currParticipants--;
    }
}