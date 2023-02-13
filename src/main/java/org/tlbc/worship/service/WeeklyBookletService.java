package org.tlbc.worship.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.tlbc.worship.model.SundayAmMenuEntity;
import org.tlbc.worship.model.SundayPeriodEnum;
import org.tlbc.worship.repository.SundayAmMenuRepository;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
@Slf4j
public class WeeklyBookletService {

    @Resource
    private SundayAmMenuRepository sundayAmMenuRepository;

    private static final ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT_THREAD_LOCAL =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd"));
    private final static String DATE_ON_REPORT = "yyyy 年 MM 月 dd 日";

    private final static String TITLE_ON_REPORT = "主日上午崇拜";
    private final static String SONG_URL_PREFIX = "https://hymns.oss-cn-shanghai.aliyuncs.com/pics/";
    private final static String SONG_FILE_NAME_SUFFIX = ".png";

    @SneakyThrows
    public ResponseEntity<byte[]> generateWordSundayBooklet(String yyyyMMdd, SundayPeriodEnum period) {
        check(yyyyMMdd);

        XWPFDocument doc = createWord(yyyyMMdd, period);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        doc.write(baos);
        doc.close();

        return new ResponseEntity<>(baos.toByteArray(), getWordHttpHeaders(yyyyMMdd), HttpStatus.OK);
    }

    @SneakyThrows
    private XWPFDocument createWord(String yyyyMMdd, SundayPeriodEnum period) {
        XWPFDocument doc = new XWPFDocument();
        if (SundayPeriodEnum.AM.equals(period)) {
            createAmReport(yyyyMMdd, doc);
            createAmNotation(doc);
        } else {
            createPmReport(yyyyMMdd, doc);
        }
        return doc;
    }

    private void createAmReport(String yyyyMMdd, XWPFDocument doc) throws ParseException {
        SundayAmMenuEntity sundayAmMenuEntity = sundayAmMenuRepository.findBySundayDate(yyyyMMdd);
        log.debug("sundayAmMenuEntity:{}", sundayAmMenuEntity);
        createReportHeaders(sundayAmMenuEntity, doc);
        createPublicWorship(sundayAmMenuEntity, doc);
        createReportFooters(sundayAmMenuEntity, doc);
    }

    private void createPmReport(String yyyyMMdd, XWPFDocument doc) {
    }

    public static void createReportHeaders(SundayAmMenuEntity sundayAmMenuEntity, XWPFDocument doc) throws ParseException {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();

        run.setText(TITLE_ON_REPORT);
        run.setFontSize(28);
        run.setFontFamily("黑体");
        run.addBreak();

        run.setText("- - - - - - - - - - - - - - - - - - -");
        run.addBreak();

        run.setText(sundayAmMenuEntity.getTheologicalSubject());
        run.setFontSize(8);
        run.setFontFamily("楷体");
        run.addBreak();

        run.setText(convertedSundayDesc(sundayAmMenuEntity.getSundayDate()));
        run.setFontFamily("黑体");
        run.addBreak();

        run.setText(hostAndPastor(sundayAmMenuEntity.getSundayDate()));
    }

    public void createPublicWorship(SundayAmMenuEntity sundayAmMenuEntity, XWPFDocument doc) {
    }

    public static void createReportFooters(SundayAmMenuEntity sundayAmMenuEntity, XWPFDocument doc) {
    }

    private static void createAmNotation(XWPFDocument doc) throws IOException, InvalidFormatException {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        addPng(run, SONG_URL_PREFIX + "齐肃立称颂主" + SONG_FILE_NAME_SUFFIX);
        run.addBreak(BreakType.COLUMN);
        addPng(run, SONG_URL_PREFIX + "祂除我罪" + SONG_FILE_NAME_SUFFIX);
        run.addBreak(BreakType.COLUMN);
        addPng(run, SONG_URL_PREFIX + "祢信实何广大" + SONG_FILE_NAME_SUFFIX);
        run.addBreak(BreakType.COLUMN);
        addPng(run, SONG_URL_PREFIX + "我何处去？" + SONG_FILE_NAME_SUFFIX);
    }

    private static final int MAX_WIDTH = 750; // Maximum width of the image in points
    private static final int MAX_HEIGHT = 750; // Maximum height of the image in points

    private static void addPng(XWPFRun run, String imgUrl) throws IOException, InvalidFormatException {
        BufferedImage originalImage = ImageIO.read(new URL(imgUrl));
//        float scale = 0.145f;
//        int scaledWidth = (int) (originalImage.getWidth() * scale);
//        int scaledHeight = (int) (originalImage.getHeight() * scale);
        double scaleFactor = Math.min((double)MAX_WIDTH / originalImage.getWidth(), (double)MAX_HEIGHT / originalImage.getHeight());
        int scaledWidth = (int) (scaleFactor * originalImage.getWidth());
        int scaledHeight = (int) (scaleFactor * originalImage.getHeight());
        BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, originalImage.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g2.dispose();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(scaledImage, "png", byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        run.addPicture(new ByteArrayInputStream(bytes), XWPFDocument.PICTURE_TYPE_PNG, "", Units.toEMU(scaledWidth), Units.toEMU(scaledHeight));
    }

    private static String hostAndPastor(String yyyyMMdd) throws ParseException {
        Date date = SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().parse(yyyyMMdd);
        SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().applyPattern(DATE_ON_REPORT);
        return SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().format(date);
    }
    private static String convertedSundayDesc(String yyyyMMdd) throws ParseException {
        Date date = SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().parse(yyyyMMdd);
        SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().applyPattern(DATE_ON_REPORT);
        return SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().format(date);
    }
    private static void check(String yyyyMMdd) throws IllegalArgumentException {
        // check if it is a Sunday
        if (!isSunday(yyyyMMdd)) {
            throw new IllegalArgumentException("NOT VALID FORMAT yyyyMMdd:" + yyyyMMdd);
        }
    }

    public static boolean isSunday(String sundayDate) {
        try {
            // 将字符串转换为日期
            Date date = SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().parse(sundayDate);

            // 将日期转换为日历
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // 检查是否为星期天
            return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;

        } catch (ParseException e) {
            log.error("Invalid sundayDate: " + sundayDate);
            return false;
        }
    }

    private static HttpHeaders getWordHttpHeaders(String yyyyMMdd) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", yyyyMMdd + "_AM.docx");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return headers;
    }

}