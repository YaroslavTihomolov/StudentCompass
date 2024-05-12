package ru.nsu.ccfit.student_compass.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class SubjectName {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Long id;

    String name;

}
