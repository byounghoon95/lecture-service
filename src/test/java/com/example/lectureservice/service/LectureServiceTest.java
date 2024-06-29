package com.example.lectureservice.service;

import com.example.lectureservice.entity.LectureApplyHistoryEntity;
import com.example.lectureservice.entity.LectureDetailEntity;
import com.example.lectureservice.entity.LectureEntity;
import com.example.lectureservice.entity.LectureRegEntity;
import com.example.lectureservice.repository.lecture.LectureRepository;
import com.example.lectureservice.repository.lectureApplyHistory.LectureApplyHistoryRepository;
import com.example.lectureservice.repository.lectureDetail.LectureRegRepository;
import com.example.lectureservice.repository.lectureReg.lectureDetail.LectureDetailRepository;
import com.example.lectureservice.repository.user.UserRepository;
import com.example.lectureservice.service.domain.Lecture;
import com.example.lectureservice.service.domain.response.LectureApplyResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest {

    @Mock
    private LectureApplyHistoryRepository lectureApplyHistoryRepository;

    @Mock
    private LectureRepository lectureRepository;

    @Mock
    private LectureDetailRepository lectureDetailRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LectureRegRepository lectureRegRepository;

    @InjectMocks
    private LectureServiceImpl lectureService;

    @DisplayName("유저가 특강을 신청한다")
    @Test
    void applyToLecture() {
        // given
        String lectureCode = "A00001";
        String title = "물리학";
        String name = "김재근";
        LocalDateTime date = LocalDateTime.of(2024, 6, 24, 13, 0,0);
        int maxParticipants = 30;
        int currParticipants = 29;
        String userId = "a1";

        LectureDetailEntity detail = LectureDetailEntity.builder()
                .name(name)
                .maxParticipants(maxParticipants)
                .currParticipants(currParticipants)
                .date(date)
                .build();

        List<LectureDetailEntity> details = List.of(detail);

        Lecture lecture = new Lecture(userId, lectureCode);
        LectureEntity entity = LectureEntity.builder()
                .lectureCode(lectureCode)
                .title(title)
                .details(details)
                .build();

        // when
        when(lectureRepository.findByLectureCodeAndId(any(),any())).thenReturn(Optional.of(entity));
        when(lectureRegRepository.isReg(any(), any())).thenReturn(Optional.empty());
        when(lectureApplyHistoryRepository.save(any())).thenReturn(new LectureApplyHistoryEntity());

        LectureApplyResponse response = lectureService.applyToLecture(lecture);

        // then
        assertThat(response.getTitle()).isEqualTo(title);
        assertThat(response.getDate()).isEqualTo(date);
        assertThat(response.getMaxParticipants()).isEqualTo(maxParticipants);
        assertThat(response.getCurrParticipants()).isEqualTo(currParticipants + 1);
    }

    @DisplayName("해당 강의를 찾을 수 없어 신청에 실패한다")
    @Test
    void applyToLecture_fail_no_lecture() {
        // given
        String lectureCode = "A00001";
        String userId = "a1";

        Lecture lecture = new Lecture(userId, lectureCode);

        // when
        when(lectureRepository.findByLectureCodeAndId(any(), any())).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> lectureService.applyToLecture(lecture))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("해당 강의를 찾을 수 없습니다");
    }

    @DisplayName("유저가 이미 특강을 신청해 실패한다")
    @Test
    void applyToLecture_fail_duplicate_user() {
        // given
        String lectureCode = "A00001";
        String userId = "a1";

        Lecture lecture = new Lecture(userId, lectureCode);

        // when
        when(lectureRepository.findByLectureCodeAndId(any(), any())).thenReturn(Optional.of(new LectureEntity()));
        when(lectureRegRepository.isReg(any(), any())).thenReturn(Optional.of(new LectureRegEntity()));

        // then
        assertThatThrownBy(() -> lectureService.applyToLecture(lecture))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 신청한 특강입니다");
    }
}