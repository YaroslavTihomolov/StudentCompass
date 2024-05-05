package ru.nsu.ccfit.student_compass.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.nsu.ccfit.student_compass.model.entity.Message;

import java.util.List;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

    List<Message> findAllByChatId(Long id, Pageable pageable);
}
