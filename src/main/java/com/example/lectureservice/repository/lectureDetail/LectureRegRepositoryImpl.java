package com.example.lectureservice.repository.lectureDetail;

import com.example.lectureservice.entity.LectureDetailEntity;
import com.example.lectureservice.entity.LectureRegEntity;
import com.example.lectureservice.service.domain.response.LectureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class LectureRegRepositoryImpl implements LectureRegRepository {

    private final LectureRegJpaRepository lectureRegRepository;

    @Override
    public LectureRegEntity save(LectureRegEntity entity) {
        return lectureRegRepository.save(entity);
    }

    @Override
    public void deleteAllInBatch() {
        lectureRegRepository.deleteAllInBatch();
    }

    @Override
    public Optional<LectureRegEntity> isReg(Long lectureId, String userId) {
        return lectureRegRepository.existsByLectureIdAndUserId(lectureId, userId);
    }

    @Override
    public List<LectureRegEntity> findByUserId(String userId) {
        return lectureRegRepository.findByUserId(userId);
    }
}
