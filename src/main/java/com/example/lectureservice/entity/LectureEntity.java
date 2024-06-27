package com.example.lectureservice.entity;

import com.example.lectureservice.service.domain.response.LectureDetailsResponse;
import com.example.lectureservice.service.domain.response.LectureResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<LectureDetailEntity> details = new ArrayList<>();

    @Builder
    public LectureEntity(String lectureCode, String title, List<LectureDetailEntity> details) {
        this.lectureCode = lectureCode;
        this.title = title;
        this.details = details;
    }

    public LectureResponse toDto() {
        List<LectureDetailsResponse> detailDto = details.stream()
                .map(LectureDetailEntity::toDto)
                .collect(Collectors.toList());

        return LectureResponse.builder()
                .lectureCode(lectureCode)
                .title(title)
                .details(detailDto)
                .build();
    }
}
