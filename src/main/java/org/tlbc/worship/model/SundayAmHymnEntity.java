package org.tlbc.worship.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "sunday_am_hymn", schema = "courseware")
public class SundayAmHymnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name_cn", unique = true, nullable = false)
    private String nameCn;
    @Column(name = "name_en", unique = true)
    private String label;
}
