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
import org.tlbc.worship.model.SundayPeriodEnum;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

@Service
@Slf4j
public class WeeklyBookletService {

    private final static String SONG_URL_PREFIX = "https://hymns.oss-cn-shanghai.aliyuncs.com/pics/";
    private final static String SONG_FILE_NAME_SUFFIX = ".png";

    @SneakyThrows
    public ResponseEntity<byte[]> generateWordSundayBooklet(String yyyyMMdd, SundayPeriodEnum period) {
        check(yyyyMMdd);

        XWPFDocument doc = createWord(yyyyMMdd, period);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        doc.write(baos);
        doc.close();

        return new ResponseEntity<>(baos.toByteArray(), getWordHeaders(yyyyMMdd), HttpStatus.OK);
    }

    @SneakyThrows
    private static XWPFDocument createWord(String yyyyMMdd, SundayPeriodEnum period) {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        if (SundayPeriodEnum.AM.equals(period)) {
            run.setText(yyyyMMdd);
            addPng(run, SONG_URL_PREFIX + "齐肃立称颂主" + SONG_FILE_NAME_SUFFIX);
            run.addBreak(BreakType.COLUMN);
            addPng(run, SONG_URL_PREFIX + "祂除我罪" + SONG_FILE_NAME_SUFFIX);
            run.addBreak(BreakType.COLUMN);
            addPng(run, SONG_URL_PREFIX + "祢信实何广大" + SONG_FILE_NAME_SUFFIX);
            run.addBreak(BreakType.COLUMN);
            addPng(run, SONG_URL_PREFIX + "我何处去？" + SONG_FILE_NAME_SUFFIX);
        } else {
            run.addBreak();
        }
        return document;
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

    private static void check(String yyyyMMdd) throws IllegalArgumentException {
        // check if it is a Sunday
    }

    private static HttpHeaders getWordHeaders(String yyyyMMdd) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", yyyyMMdd + "_AM.docx");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return headers;
    }

}