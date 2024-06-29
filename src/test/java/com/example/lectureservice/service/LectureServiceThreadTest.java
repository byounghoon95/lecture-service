package com.example.lectureservice.service;

import com.example.lectureservice.entity.LectureDetailEntity;
import com.example.lectureservice.entity.LectureEntity;
import com.example.lectureservice.entity.UserEntity;
import com.example.lectureservice.repository.lecture.LectureRepository;
import com.example.lectureservice.repository.lectureApplyHistory.LectureApplyHistoryRepository;
import com.example.lectureservice.repository.lectureDetail.LectureRegRepository;
import com.example.lectureservice.repository.lectureReg.lectureDetail.LectureDetailRepository;
import com.example.lectureservice.repository.user.UserRepository;
import com.example.lectureservice.service.domain.Lecture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class LectureServiceThreadTest {

    @Autowired
    private LectureApplyHistoryRepository lectureApplyHistoryRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LectureRegRepository lectureRegRepository;

    @Autowired
    private LectureDetailRepository lectureDetailRepository;

    @Autowired
    private LectureServiceImpl lectureService;

    void setUp() {
        String lectureCode = "A00001";
        String title = "물리학";
        String name = "김재근";
        LocalDateTime date = LocalDateTime.of(2024, 6, 24, 13, 0,0);
        int maxParticipants = 30;
        int currParticipants = 20;

        LectureEntity lecture = LectureEntity.builder()
                .lectureCode(lectureCode)
                .title(title)
                .build();
        LectureDetailEntity detail = LectureDetailEntity.builder()
                .lecture(lecture)
                .name(name)
                .date(date)
                .maxParticipants(maxParticipants)
                .currParticipants(currParticipants)
                .build();

        for (int i = 1; i < 11; i++) {
            userRepository.save(new UserEntity("a" + i));
        }
        lectureRepository.save(lecture);

        lectureDetailRepository.save(detail);
    }

    void tearDown() {
        lectureDetailRepository.deleteAllInBatch();
        lectureRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
        lectureRegRepository.deleteAllInBatch();
        lectureApplyHistoryRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("동시에 신청할 때 최대 인원을 넘지 않도록 동시성을 보장한다")
    void applyToLecture_concurrent_request() throws InterruptedException {
        // given
        Long lectureId = 1L;
        String lectureCode = "A00001";
        int maxParticipants = 30;

        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> setUp())
        ).join();

        // when
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a1", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a2", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a3", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a4", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a5", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a6", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a7", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a8", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a9", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a10",lectureCode, lectureId)))
        ).join();

        LectureDetailEntity findCount = lectureDetailRepository.findById(lectureId).get();

        // then
        assertThat(maxParticipants).isEqualTo(findCount.getCurrParticipants());

        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> tearDown())
        ).join();
    }

    @Test
    @DisplayName("동시에 신청할 때 최대 인원을 넘으면 에러를 반환한다")
    void applyToLecture_fail_concurrent_request_over_maxParticipants() throws InterruptedException {
        // given
        Long lectureId = 1L;
        String lectureCode = "A00001";
        int maxParticipants = 30;

        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> setUp())
        ).join();

        // when then
        List<CompletableFuture<Void>> futures = List.of(
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a1", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a2", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a3", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a4", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a5", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a6", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a7", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a8", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a9", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a10", lectureCode, lectureId))),
                CompletableFuture.runAsync(() -> lectureService.applyToLecture(new Lecture("a11", lectureCode, lectureId)))
        );

        Throwable exception = futures.stream()
                .map(future -> {
                    try {
                        future.join();
                        return null;
                    } catch (CompletionException ex) {
                        return ex.getCause();
                    }
                })
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);

        LectureDetailEntity findCount = lectureDetailRepository.findById(lectureId).get();

        // then
        assertThat(maxParticipants).isEqualTo(findCount.getMaxParticipants());
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("특강이 마감되었습니다");

        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> tearDown())
        ).join();
    }
}