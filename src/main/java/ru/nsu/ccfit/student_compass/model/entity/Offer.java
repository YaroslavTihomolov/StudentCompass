package ru.nsu.ccfit.student_compass.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Offer {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Task task;

    private BigDecimal price;

}
