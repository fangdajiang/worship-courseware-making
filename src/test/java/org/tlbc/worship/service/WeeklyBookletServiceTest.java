package org.tlbc.worship.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class WeeklyBookletServiceTest {
    @Resource
    private WeeklyBookletService weeklyBookletService;

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
    void hostAndPastor() throws ParseException {
        String s = WeeklyBookletService.hostAndPastor("20230129");
        log.debug("s: {}", s);
        assertFalse(s.isEmpty());
    }

    @Test
    void isSunday() {
    }
}