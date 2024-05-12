package ru.nsu.ccfit.student_compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.student_compass.model.entity.Task;

import java.util.List;
import java.util.Set;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllBySubjectNameNameIn(Set<String> subjectNames);

}
