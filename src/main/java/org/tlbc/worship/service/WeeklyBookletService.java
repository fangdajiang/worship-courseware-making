package org.tlbc.worship.service;

import cn.hutool.core.lang.Assert;
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
import org.tlbc.worship.model.SundayAmHymnArrangementEntity;
import org.tlbc.worship.model.SundayAmMenuEntity;
import org.tlbc.worship.model.SundayCoreLessonArrangementEntity;
import org.tlbc.worship.model.SundayPeriodEnum;
import org.tlbc.worship.repository.SundayAmHymnArrangementRepository;
import org.tlbc.worship.repository.SundayAmMenuRepository;
import org.tlbc.worship.repository.SundayCoreLessonArrangementRepository;

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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class WeeklyBookletService {

    @Resource
    private SundayAmMenuRepository sundayAmMenuRepository;
    @Resource
    private SundayAmHymnArrangementRepository sundayAmHymnArrangementRepository;
    @Resource
    private SundayCoreLessonArrangementRepository sundayCoreLessonArrangementRepository;

    private final static String DATE_FORMAT = "yyyyMMdd";
    private final static String DATE_ON_REPORT = "主后 yyyy 年 MM 月 dd 日";
    private final static String HOST_PASTOR_ON_REPORT = "领会：%s 证道：%s牧师";
    private final static String CORE_LESSON_ON_REPORT = "9-10AM核心课程：";
    private final static Integer CORE_LESSON_MIN_COUNT = 1;
    private final static Integer AM_HYMN_COUNT = 4;

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
//            createAmNotation(doc);
        } else {
            createPmReport(yyyyMMdd, doc);
        }
        return doc;
    }

    private void createAmReport(String yyyyMMdd, XWPFDocument doc) throws ParseException {
        SundayAmMenuEntity sundayAmMenuEntity = sundayAmMenuRepository.findBySundayDate(yyyyMMdd);
        log.debug("sundayAmMenuEntity:{}", sundayAmMenuEntity);
        if (null == sundayAmMenuEntity) {
            throw new IllegalArgumentException("Not Found:" + yyyyMMdd);
        }
        createReportHeaders(sundayAmMenuEntity, doc);
        createPublicWorship(sundayAmMenuEntity.getSundayDate(), doc);
        createPublicWorshipComment(sundayAmMenuEntity.getSundayDate(), doc);
        createReportFooters(sundayAmMenuEntity, doc);
    }

    private void createPmReport(String yyyyMMdd, XWPFDocument doc) {
    }

    public void createReportHeaders(SundayAmMenuEntity sundayAmMenuEntity, XWPFDocument doc) throws ParseException {
        createReportTitle(doc);
        createReportTheologicalSubject(sundayAmMenuEntity.getTheologicalSubject(), doc);
        createReportTeaching(sundayAmMenuEntity, doc);
    }
    public static void createReportTitle(XWPFDocument doc) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(14);
        run.setFontFamily("黑体");
        run.setText(TITLE_ON_REPORT);
        run.addBreak();
        run.setText("————————————————————");
    }
    public static void createReportTheologicalSubject(String theologicalSubject, XWPFDocument doc) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(8);
        run.setFontFamily("楷体");
        run.setText(theologicalSubject);
    }
    public void createReportTeaching(SundayAmMenuEntity sundayAmMenuEntity, XWPFDocument doc) throws ParseException {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(8);
        run.setFontFamily("黑体");
        run.setText(convertedSundayDesc(sundayAmMenuEntity.getSundayDate()));
        run.addBreak();
        run.setText(hostAndPreacher(sundayAmMenuEntity));
        run.addBreak();
        run.setText(coreLessonArrangement(sundayAmMenuEntity.getSundayDate()));
    }

    public void createPublicWorship(String sundayDesc, XWPFDocument doc) {
        SundayAmMenuEntity sundayAmMenuEntity = sundayAmMenuRepository.findBySundayDate(sundayDesc);
        List<SundayAmHymnArrangementEntity> hymnArrangements = sundayAmHymnArrangementRepository.findBySundayDate(sundayDesc);
        Assert.isTrue(hymnArrangements.size() == AM_HYMN_COUNT,
                "am hymns count must == " + AM_HYMN_COUNT);

        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(14);
        run.setFontFamily("黑体");
        run.setText("10:15 公共崇拜");
        run.addBreak();
        run.setText("• 预备诗歌");
        run.addBreak();
        run.setText("• 欢迎和报告");
        run.addBreak();
        run.setText("• 宣召：" + sundayAmMenuEntity.getCallToWorship());
        run.addBreak();
        run.setText("• 诗歌：《" + hymnArrangements.get(0).getNameCn() + "》");
        run.addBreak();
        run.setText("• 赞美祷告（" + sundayAmMenuEntity.getPraisePrayer() + "）");
        run.addBreak();
        run.setText("• 旧约读经：" + sundayAmMenuEntity.getOldTestamentScripture() + "(" + sundayAmMenuEntity.getOldTestamentReader() + ")");
        run.addBreak();
        run.setText("• 诗歌：《" + hymnArrangements.get(1).getNameCn() + "》");
        run.addBreak();
        run.setText("• 教理：" + sundayAmMenuEntity.getDoctrineAbbr());
        run.addBreak();
        run.setText("• 诗歌：《" + hymnArrangements.get(2).getNameCn() + "》");
        run.addBreak();
        run.setText("• 新约读经：" + sundayAmMenuEntity.getNewTestamentScripture() + "(" + sundayAmMenuEntity.getNewTestamentReader() + ")");
        run.addBreak();
        run.setText("• 牧祷祈求（" + sundayAmMenuEntity.getPastorPraying() + "）");
        run.addBreak();
        run.setText("• 诗歌：《" + hymnArrangements.get(3).getNameCn() + "》");
        run.addBreak();
        run.setText("• 奉献【请将准备好的奉献放入信封后投入奉献袋】");
        run.addBreak();
        run.setText("• 感恩祷告（" + sundayAmMenuEntity.getThanksPrayer() + "）");
        run.addBreak();
        run.setText("• 《" + sundayAmMenuEntity.getEndHymn() + "》【此后儿童请听领会安排前往相应的教室】");
        run.addBreak();
        if (hostCommunion(sundayDesc)) {
            run.setText("• 主餐†（" + sundayAmMenuEntity.getPreacher() + "）");
            run.addBreak();
        }
        run.setText("• 讲道信息：" + sundayAmMenuEntity.getSermonTitle());
        run.addBreak();
        run.setText("• 回应诗歌：《" + sundayAmMenuEntity.getSermonHymn() + "》");
        run.addBreak();
        run.setText("• 祝福††");
    }
    public void createPublicWorshipComment(String sundayDesc, XWPFDocument doc) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(8);
        run.setFontFamily("黑体");
        if (hostCommunion(sundayDesc)) {
            run.setText("† 非本教会成员需和长老面谈后，获得长老准许方可同领主餐。详见封底通知部分");
            run.addBreak();
        }
        run.setText("†† 祝福后，请在原座默想片刻随司琴的音乐而结束聚会。");
    }

    public static boolean hostCommunion(String sundayDate) {
        int year = Integer.parseInt(sundayDate.substring(0, 4));
        int month = Integer.parseInt(sundayDate.substring(4, 6));
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        DayOfWeek dayOfWeek = firstDayOfMonth.getDayOfWeek();
        int daysUntilFirstSunday = DayOfWeek.SUNDAY.getValue() - dayOfWeek.getValue();
        if (daysUntilFirstSunday < 0) {
            daysUntilFirstSunday += 7;
        }
        return firstDayOfMonth.plusDays(daysUntilFirstSunday).format(DateTimeFormatter.ofPattern(DATE_FORMAT)).equals(sundayDate);
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

    public String coreLessonArrangement(String yyyyMMdd) {
        List<SundayCoreLessonArrangementEntity> coreLessonArrangement = sundayCoreLessonArrangementRepository.findBySundayDate(yyyyMMdd);
        Assert.isTrue(coreLessonArrangement.size() >= CORE_LESSON_MIN_COUNT,
                "core lessons count must >= " + CORE_LESSON_MIN_COUNT);
        StringBuilder sb = new StringBuilder(CORE_LESSON_ON_REPORT);
        for (SundayCoreLessonArrangementEntity oneArrangement:
             coreLessonArrangement) {
            sb.append(oneArrangement.getName())
                    .append("(")
                    .append(oneArrangement.getSerialNumber())
                    .append(")")
                    .append("|");
        }
        return sb.substring(0, sb.length() - 1);
    }
    public static String hostAndPreacher(SundayAmMenuEntity sundayAmMenuEntity) {
        return String.format(HOST_PASTOR_ON_REPORT, sundayAmMenuEntity.getHost(), sundayAmMenuEntity.getPreacher());
    }
    private static String convertedSundayDesc(String yyyyMMdd) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = sdf.parse(yyyyMMdd);
        sdf.applyPattern(DATE_ON_REPORT);
        return sdf.format(date);
    }
    private static void check(String yyyyMMdd) throws IllegalArgumentException {
        // check if it is a Sunday
        if (!isSunday(yyyyMMdd)) {
            throw new IllegalArgumentException("NOT VALID SUNDAY:" + yyyyMMdd);
        }
    }

    public static boolean isSunday(String sundayDate) {
        try {
            // 将字符串转换为日期
            Date date = new SimpleDateFormat(DATE_FORMAT).parse(sundayDate);

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