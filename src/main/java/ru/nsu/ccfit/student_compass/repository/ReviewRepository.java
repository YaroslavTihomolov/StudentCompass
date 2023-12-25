package ru.nsu.ccfit.student_compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.student_compass.model.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
