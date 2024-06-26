package com.example.lectureservice.service;

import com.example.lectureservice.service.domain.Lecture;
import com.example.lectureservice.service.domain.response.LectureApplyResponse;
import com.example.lectureservice.service.domain.response.LectureResponse;

import java.util.List;

public interface LectureService {
    LectureApplyResponse applyToLecture(Lecture request);
    Boolean isAppliedToLecture(String userId, String lectureCode);

    List<LectureResponse> selectLectureList();
}
