package org.tlbc.worship.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.tlbc.worship.repository.SundayAmMenuRepository;

import javax.annotation.Resource;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j @SpringBootTest
class WeeklyBookletServiceTest {
    @Resource
    private WeeklyBookletService weeklyBookletService;
    @Resource
    private SundayAmMenuRepository sundayAmMenuRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void generateWordSundayBooklet() {
    }

    @Test
    void createReportHeaders() {
    }

    @Test
    void createPublicWorship() {
    }

    @Test
    void createReportFooters() {
    }

    @Test
    void hostAndPastor() {
        String s = WeeklyBookletService.hostAndPreacher(sundayAmMenuRepository.findBySundayDate("20230129"));
        log.debug("s: {}", s);
        assertFalse(s.isEmpty());
    }

    @Test
    void isSunday() {
    }

    @Test
    void coreLessonArrangement() throws ParseException {
        String s = weeklyBookletService.coreLessonArrangement("20230129");
        log.debug("s: {}", s);
        assertFalse(s.isEmpty());
    }
}