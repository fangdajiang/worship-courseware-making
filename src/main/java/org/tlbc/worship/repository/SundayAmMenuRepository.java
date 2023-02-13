package org.tlbc.worship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tlbc.worship.model.SundayAmMenuEntity;

@Repository
public interface SundayAmMenuRepository extends JpaRepository<SundayAmMenuEntity, Integer> {
    SundayAmMenuEntity findBySundayDate(String yyyyMMdd);

}
