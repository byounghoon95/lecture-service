package com.example.lectureservice.service.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureDetailsResponse {
    private String name;
    private LocalDateTime date;
    private int maxParticipants;
    private int currParticipants;
}