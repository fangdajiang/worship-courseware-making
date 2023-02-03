package org.tlbc.worship.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.tlbc.worship.model.SundayPeriodEnum;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
@Slf4j
public class WeeklyBookletService {

    @SneakyThrows
    public ResponseEntity<Resource> generateWordSundayBooklet(String yyyyMMdd, SundayPeriodEnum period) {
        check(yyyyMMdd);

        XWPFDocument document = createWord(yyyyMMdd, period);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.write(out);
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        document.close();

        return ResponseEntity.ok().headers(getWordHeaders(yyyyMMdd)).contentLength(out.size())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(in));
    }

    private static XWPFDocument createWord(String yyyyMMdd, SundayPeriodEnum period) {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        if (SundayPeriodEnum.AM.equals(period)) {
            run.setText(yyyyMMdd);
            run.addBreak();

        } else {
            run.addBreak();

        }
        return document;
    }

    private static void check(String yyyyMMdd) throws IllegalArgumentException {
        // check if it is a Sunday
    }

    private static HttpHeaders getWordHeaders(String yyyyMMdd) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Disposition", "attachment; filename=" + yyyyMMdd + "_AM.docx");
        return headers;
    }

}