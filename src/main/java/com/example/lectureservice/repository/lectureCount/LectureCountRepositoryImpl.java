package com.example.lectureservice.repository.lectureCount;

import com.example.lectureservice.entity.LectureCountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class LectureCountRepositoryImpl implements LectureCountRepository{
    private final LectureCountJpaRepository lectureCountRepository;

    @Override
    public Optional<LectureCountEntity> findByLectureCodeForUpdate(String lectureCode) {
        return lectureCountRepository.findByLectureCodeForUpdate(lectureCode);
    }

    @Override
    public LectureCountEntity save(LectureCountEntity entity) {
        return lectureCountRepository.save(entity);
    }

    @Override
    public void flush() {
        lectureCountRepository.flush();
    }

    @Override
    public void deleteAllInBatch() {
        lectureCountRepository.deleteAllInBatch();
    }
}
