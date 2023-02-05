package org.tlbc.worship.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tlbc.worship.model.SundayPeriodEnum;
import org.tlbc.worship.service.WeeklyBookletService;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@RestController @Slf4j @CrossOrigin
@RequestMapping("/booklets")
public class WeeklyBookletController {
    @javax.annotation.Resource
    private WeeklyBookletService weeklyBookletService;

    @GetMapping(value = "/word")
    public ResponseEntity<byte[]> generateWord(@RequestParam("sundayDate") String yyyyMMdd) {
        return weeklyBookletService.generateWordSundayBooklet(yyyyMMdd, SundayPeriodEnum.AM);
    }

    @GetMapping(value = "/pdf/{title}")
    public void generatePDF(HttpServletResponse response,
                            @PathVariable String title) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + title + ".pdf");

        Document document = new Document();
        OutputStream outputStream = response.getOutputStream();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        document.add(new Paragraph("PDF Document Title: " + title));
        document.close();
    }
}
