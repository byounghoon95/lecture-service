package com.example.lectureservice.repository.lectureApplyHistory;

import com.example.lectureservice.entity.LectureApplyHistoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LectureApplyHistoryRepositoryImpl implements LectureApplyHistoryRepository {
    private final LectureApplyHistoryJpaRepository lectureApplyHistoryRepository;

    @Override
    public LectureApplyHistoryEntity save(LectureApplyHistoryEntity entity) {
        return lectureApplyHistoryRepository.save(entity);
    }

    @Override
    public void deleteAllInBatch() {
        lectureApplyHistoryRepository.deleteAllInBatch();
    }

}
