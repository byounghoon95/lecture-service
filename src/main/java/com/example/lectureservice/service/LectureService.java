package com.example.lectureservice.service;

import com.example.lectureservice.service.domain.Lecture;
import com.example.lectureservice.service.domain.response.LectureApplyResponse;
import com.example.lectureservice.service.domain.response.LectureResponse;

import java.util.List;

public interface LectureService {
    LectureApplyResponse applyToLecture(Lecture request);
    List<LectureResponse> selectLectureList();
    LectureResponse selectLectureDetailList(String lectureCode);
    List<LectureApplyResponse> isAppliedLecture(String userId);
}
