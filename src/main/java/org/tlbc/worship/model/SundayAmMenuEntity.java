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
    @Column(name = "sunday_date_desc", unique = true, nullable = false)
    private String sundayDateDesc;
    @Column(name = "host", nullable = false)
    private String host;
    @Column(name = "pastor", nullable = false)
    private String pastor;
}