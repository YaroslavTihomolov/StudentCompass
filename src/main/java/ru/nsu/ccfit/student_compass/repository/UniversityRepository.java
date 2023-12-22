package ru.nsu.ccfit.student_compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.student_compass.model.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

    University findByName(String name);

}
