package org.tlbc.worship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tlbc.worship.model.SundayCoreLessonArrangementEntity;

import java.util.List;

@Repository
public interface SundayCoreLessonArrangementRepository extends JpaRepository<SundayCoreLessonArrangementEntity, Integer> {
    List<SundayCoreLessonArrangementEntity> findBySundayDate(String yyyyMMdd);

}
