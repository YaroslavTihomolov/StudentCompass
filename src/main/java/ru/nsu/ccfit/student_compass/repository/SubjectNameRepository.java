package ru.nsu.ccfit.student_compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.student_compass.model.entity.SubjectName;

public interface SubjectNameRepository extends JpaRepository<SubjectName, Long> {

    SubjectName findSubjectNameByName(String name);

}
