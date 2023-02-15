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
    @Column(name = "doctrine", nullable = false)
    private String doctrine;
    @Column(name = "new_testament_reader", nullable = false)
    private String newTestamentReader;
    @Column(name = "pastor_praying", nullable = false)
    private String pastorPraying;
    @Column(name = "thanks_prayer", nullable = false)
    private String thanksPrayer;
    @Column(name = "end_hymn", nullable = false)
    private String endHymn;
    @Column(name = "sermon", nullable = false)
    private String sermon;
    @Column(name = "sermon_hymn", nullable = false)
    private String sermonHymn;
}
