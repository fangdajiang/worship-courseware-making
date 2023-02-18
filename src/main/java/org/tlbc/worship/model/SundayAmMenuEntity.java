package org.tlbc.worship.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "sunday_am_menu", schema = "courseware")
public class SundayAmMenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "sunday_date", unique = true, nullable = false)
    private String sundayDate;

    @Column(name = "theological_subject", nullable = false)
    private String theologicalSubject;
    @Column(name = "host", nullable = false)
    private String host;
    @Column(name = "preacher", nullable = false)
    private String preacher;
    @Column(name = "call_to_worship", nullable = false)
    private String callToWorship;
    @Column(name = "praise_prayer", nullable = false)
    private String praisePrayer;
    @Column(name = "old_testament_reader", nullable = false)
    private String oldTestamentReader;
    @Column(name = "old_testament_scripture", nullable = false)
    private String oldTestamentScripture;
    @Column(name = "doctrine_abbr")
    private String doctrineAbbr;
    @Column(name = "doctrine_title")
    private String doctrineTitle;
    @Column(name = "doctrine_content")
    private String doctrineContent;
    @Column(name = "new_testament_reader", nullable = false)
    private String newTestamentReader;
    @Column(name = "new_testament_scripture", nullable = false)
    private String newTestamentScripture;
    @Column(name = "pastor_praying", nullable = false)
    private String pastorPraying;
    @Column(name = "thanks_prayer", nullable = false)
    private String thanksPrayer;
    @Column(name = "end_hymn", nullable = false)
    private String endHymn;
    @Column(name = "sermon_title", nullable = false)
    private String sermonTitle;
    @Column(name = "sermon_outline")
    private String sermonOutline;
    @Column(name = "sermon_hymn", nullable = false)
    private String sermonHymn;

    @Column(name = "worship_notice1", columnDefinition = "varchar(2048)", nullable = false)
    private String worshipNotice1;
    @Column(name = "worship_notice2", columnDefinition = "varchar(2048)", nullable = false)
    private String worshipNotice2;

    @Column(name = "usher")
    private String usher;
    @Column(name = "servant_for_kids")
    private String servantForKids;
    @Column(name = "servant_for_audio_video")
    private String servantForAudioVideo;
    @Column(name = "duty_manager")
    private String dutyManager;
}
