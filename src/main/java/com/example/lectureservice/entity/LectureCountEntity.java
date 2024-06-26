package com.example.lectureservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "LECTURE_COUNT")
@Entity
public class LectureCountEntity {
    @Id
    @Column(name = "LECTURE_CODE")
    private String lectureCode;

    @Column(nullable = false, name = "CURR_PARTICIPANTS")
    private int currParticipants = 0;

    @Version
    @Column(name = "VERSION")
    private Long version;

    public LectureCountEntity(String lectureCode, int currParticipants) {
        this.lectureCode = lectureCode;
        this.currParticipants = currParticipants;
    }

    public void addCurrParticipants() {
        this.currParticipants++;
    }
}