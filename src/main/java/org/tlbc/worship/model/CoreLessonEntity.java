package org.tlbc.worship.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "core_lesson", schema = "courseware")
public class CoreLessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "core_lesson_name", unique = true, nullable = false)
    private String coreLessonName;
    @Column(name = "core_lesson_teacher", nullable = false)
    private String coreLessonTeacher;
    @Column(name = "core_lesson_quantity", nullable = false)
    private Integer coreLessonQuantity;
}
