package com.example.lectureservice.entity;

import com.example.lectureservice.enums.Status;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Table(name = "LECTURE_APPLY_HISTORY")
@Entity
public class LectureApplyHistoryEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "LECTURE_CODE")
    private String lectureCode;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;

    @Builder
    public LectureApplyHistoryEntity(String userId, String lectureCode) {
        this.userId = userId;
        this.lectureCode = lectureCode;
    }
}