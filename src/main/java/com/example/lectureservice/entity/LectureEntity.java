package com.example.lectureservice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Table(name = "LECTURE")
@Entity
public class LectureEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LECTURE_CODE", nullable = false, unique = true)
    private String lectureCode;

    @Column(nullable = false, name = "TITLE")
    private String title;

    @Column(nullable = false, name = "NAME")
    private String name;

    @Column(nullable = false, name = "MAX_PARTICIPANTS")
    private int maxParticipants;

    @Column(nullable = false, name = "DATE")
    private LocalDateTime date;

    @Builder
    public LectureEntity(String lectureCode, String title, String name, int maxParticipants, LocalDateTime date) {
        this.lectureCode = lectureCode;
        this.title = title;
        this.name = name;
        this.maxParticipants = maxParticipants;
        this.date = date;
    }
}
