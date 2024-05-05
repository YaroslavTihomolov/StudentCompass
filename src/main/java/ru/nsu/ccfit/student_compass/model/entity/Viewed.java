package ru.nsu.ccfit.student_compass.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "message")
@Getter
@Setter
@Accessors(chain = true)
public class Viewed {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Long id;

    private boolean isViewed;

    private User user;
}
