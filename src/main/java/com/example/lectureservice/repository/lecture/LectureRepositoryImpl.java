package com.example.lectureservice.repository.lecture;

import com.example.lectureservice.entity.LectureEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class LectureRepositoryImpl implements LectureRepository{

    private final LectureJpaRepository lectureRepository;

    @Override
    public LectureEntity save(LectureEntity entity) {
        return lectureRepository.save(entity);
    }

    @Override
    public List<LectureEntity> findLecturesAfterDate(LocalDateTime now) {
        return lectureRepository.findLecturesAfterDate(now);
    }

    @Override
    public void deleteAllInBatch() {
        lectureRepository.deleteAllInBatch();
    }

    @Override
    public Optional<LectureEntity> findByLectureCodeAndId(String lectureCode, Long lectureDetailId) {
        return lectureRepository.findByLectureCodeAndId(lectureCode, lectureDetailId);
    }

    @Override
    public Optional<LectureEntity> findLectureDetail(String lectureCode) {
        return lectureRepository.findLectureDetail(lectureCode);
    }
}
