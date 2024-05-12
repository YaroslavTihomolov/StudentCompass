package ru.nsu.ccfit.student_compass.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Task {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Long id;

    private String title;

    private String description;

    private String startPrice;

    @Setter(value = AccessLevel.PRIVATE)
    private boolean isClose;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Offer> offers;

    @OneToOne
    private SubjectName subjectName;

    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    public Task closeTask() {
        this.isClose = true;
        return this;
    }

}
