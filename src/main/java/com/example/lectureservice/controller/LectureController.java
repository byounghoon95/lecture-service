package com.example.lectureservice.controller;

import com.example.lectureservice.controller.dto.LectureRequest;
import com.example.lectureservice.service.LectureService;
import com.example.lectureservice.service.domain.response.LectureApplyResponse;
import com.example.lectureservice.service.domain.response.LectureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {
    private final LectureService lectureService;

    /**
     * 특강 신청
     * */
    @PostMapping("/apply")
    public ResponseEntity<LectureApplyResponse> apply(@RequestBody LectureRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(lectureService.applyToLecture(request.toDomain()));
    }

    /**
     * 특강 신청 완료 여부 조회
     * 해당 유저가 신청한 특강이 조회됨
     * */
    @GetMapping("/application/{userId}")
    public ResponseEntity<List<LectureApplyResponse>> isApplied(@PathVariable("userId") String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(lectureService.isAppliedLecture(userId));
    }

    /**
     * 현재 신청할 수 있는 특강 리스트 조회
     * */
    @GetMapping("")
    public ResponseEntity<List<LectureResponse>> selectLectureList() {
        return ResponseEntity.status(HttpStatus.OK).body(lectureService.selectLectureList());
    }

    /**
     * 한 특강의 시간별 강의 정보를 조회
     * */
    @GetMapping("/{lectureCode}")
    public ResponseEntity<LectureResponse> selectLectureDetailList(@PathVariable("lectureCode") String lectureCode) {
        return ResponseEntity.status(HttpStatus.OK).body(lectureService.selectLectureDetailList(lectureCode));
    }
}
