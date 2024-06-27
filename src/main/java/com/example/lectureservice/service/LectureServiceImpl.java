package com.example.lectureservice.service;

import com.example.lectureservice.entity.LectureDetailEntity;
import com.example.lectureservice.entity.LectureEntity;
import com.example.lectureservice.entity.LectureRegEntity;
import com.example.lectureservice.enums.Status;
import com.example.lectureservice.repository.lecture.LectureRepository;
import com.example.lectureservice.repository.lectureApplyHistory.LectureApplyHistoryRepository;
import com.example.lectureservice.repository.lectureDetail.LectureRegRepository;
import com.example.lectureservice.repository.lectureReg.lectureDetail.LectureDetailRepository;
import com.example.lectureservice.service.domain.Lecture;
import com.example.lectureservice.service.domain.response.LectureApplyResponse;
import com.example.lectureservice.service.domain.response.LectureResponse;
import com.example.lectureservice.util.LockManager;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class LectureServiceImpl implements LectureService{

    private final LectureRepository lectureRepository;
    private final LectureDetailRepository lectureDetailRepository;
    private final LectureApplyHistoryRepository lectureApplyHistoryRepository;
    private final LectureRegRepository lectureRegRepository;

    @Transactional
    @Override
    public LectureApplyResponse applyToLecture(Lecture lecture) {
        String key = "lecture_" + lecture.getLectureCode();

        LockManager.lock(key);

        try {
            LectureEntity findLecture = findLecture(lecture);

            checkAppliedLecture(lecture);

            addParticipantsForUpdate(lecture, findLecture.getDetails().get(0));

            return LectureApplyResponse.of(findLecture);
        } finally {
            LockManager.unlock(key);
        }
    }

    private void addParticipantsForUpdate(Lecture lecture, LectureDetailEntity lectureDetail) {
        for (int i = 0; i < 3; i++) {
            try {
                if (lectureDetail.getCurrParticipants() >= lectureDetail.getMaxParticipants()) {
                    lecture.updateStatus(Status.FAIL);
                    lectureApplyHistoryRepository.save(lecture.toLectureApplyEntity());
                    throw new IllegalStateException("특강이 마감되었습니다");
                }

                lectureDetail.addCurrParticipants();
                lectureDetailRepository.flush();

                lecture.updateStatus(Status.SUCCESS);
                lectureApplyHistoryRepository.save(lecture.toLectureApplyEntity());

                lectureRegRepository.save(new LectureRegEntity(lectureDetail, lecture.getUserId()));

                break;
            } catch (OptimisticLockingFailureException e) {
                try {
                    lectureDetail.minusCurrParticipants();
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private void checkAppliedLecture(Lecture lecture) {
        lectureRegRepository.isReg(lecture.getLectureDetailId(), lecture.getUserId())
                .ifPresent(entity -> {
                    throw new IllegalStateException("이미 신청한 특강입니다");
                });
    }

    private LectureEntity findLecture(Lecture lecture) {
        return lectureRepository.findByLectureCodeAndId(lecture.getLectureCode(), lecture.getLectureDetailId())
                .orElseThrow(() -> new NullPointerException("해당 강의를 찾을 수 없습니다"));
    }

    @Override
    public List<LectureResponse> selectLectureList() {
        return lectureRepository.findLecturesAfterDate(LocalDateTime.now().minusHours(3)).stream()
                .map(LectureEntity::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LectureResponse selectLectureDetailList(String lectureCode) {
        return lectureRepository.findLectureDetail(lectureCode)
                .orElseThrow(() -> {
                    throw new IllegalStateException("등록된 강의가 없습니다");
                }).toDto();
    }

    @Override
    public List<LectureApplyResponse> isAppliedLecture(String userId) {
        return lectureRegRepository.findByUserId(userId).stream()
                .map(LectureRegEntity::toDto)
                .collect(Collectors.toList());
    }
}