package org.tlbc.worship.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.tlbc.worship.model.SundayAmMenuEntity;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j @SpringBootTest
class SundayAmMenuRepositoryTest {
    @Resource
    private SundayAmMenuRepository sundayAmMenuRepository;

    @Test
    void findBySundayDate() {
        SundayAmMenuEntity sundayAmMenuEntity = sundayAmMenuRepository.findBySundayDate("20230129");
        log.debug("sundayAmMenuEntity: {}", sundayAmMenuEntity);
        assertNotNull(sundayAmMenuEntity);
    }
}