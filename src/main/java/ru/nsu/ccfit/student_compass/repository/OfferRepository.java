package ru.nsu.ccfit.student_compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.student_compass.model.entity.Offer;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAllByTaskId(Long taskId);

}
