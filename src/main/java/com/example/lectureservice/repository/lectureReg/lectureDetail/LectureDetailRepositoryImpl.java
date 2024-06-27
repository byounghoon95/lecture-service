package com.example.lectureservice.repository.lectureReg.lectureDetail;

import com.example.lectureservice.entity.LectureDetailEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class LectureDetailRepositoryImpl implements LectureDetailRepository {

    private final LectureDetailJpaRepository lectureDetailRepository;

    @Override
    public LectureDetailEntity save(LectureDetailEntity entity) {
        return lectureDetailRepository.save(entity);
    }

    @Override
    public void flush() {
        lectureDetailRepository.flush();
    }

    @Override
    public Optional<LectureDetailEntity> findById(Long id) {
        return lectureDetailRepository.findById(id);
    }

    @Override
    public void deleteAllInBatch() {
        lectureDetailRepository.deleteAllInBatch();
    }
}
