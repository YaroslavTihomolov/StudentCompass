package ru.nsu.ccfit.student_compass.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ccfit.student_compass.model.entity.Course;

import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class University {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String imageUrl;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Course> courses;

}
