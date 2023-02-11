package org.tlbc.worship.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "sunday_am_hymn_arrangement", schema = "courseware")
public class SundayAmHymnArrangementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "sunday_date", nullable = false)
    private String sundayDate;
    @Column(name = "name_cn", nullable = false)
    private String nameCn;
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "serial_number", nullable = false)
    private Integer serialNumber;
}
