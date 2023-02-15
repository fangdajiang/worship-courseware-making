package org.tlbc.worship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tlbc.worship.model.SundayAmHymnArrangementEntity;

import java.util.List;

@Repository
public interface SundayAmHymnArrangementRepository extends JpaRepository<SundayAmHymnArrangementEntity, Integer> {
    List<SundayAmHymnArrangementEntity> findBySundayDate(String yyyyMMdd);

}
