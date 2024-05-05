package ru.nsu.ccfit.student_compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.student_compass.model.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    boolean existsByName(String name);
}
