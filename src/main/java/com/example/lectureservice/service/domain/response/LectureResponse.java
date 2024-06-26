package com.example.lectureservice.service.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureResponse {
    private String lectureCode;
    private String title;
    private List<LectureDetailsResponse> details;
}