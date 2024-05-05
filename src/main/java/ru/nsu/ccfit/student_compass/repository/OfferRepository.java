package ru.nsu.ccfit.student_compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.student_compass.model.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
