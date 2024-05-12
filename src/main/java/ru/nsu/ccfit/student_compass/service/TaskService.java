package ru.nsu.ccfit.student_compass.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.student_compass.model.dto.OfferDto;
import ru.nsu.ccfit.student_compass.model.dto.TaskDto;
import ru.nsu.ccfit.student_compass.model.entity.Offer;
import ru.nsu.ccfit.student_compass.model.entity.Task;
import ru.nsu.ccfit.student_compass.model.mapper.OfferMapper;
import ru.nsu.ccfit.student_compass.model.mapper.TaskMapper;
import ru.nsu.ccfit.student_compass.repository.OfferRepository;
import ru.nsu.ccfit.student_compass.repository.SubjectRepository;
import ru.nsu.ccfit.student_compass.repository.TaskRepository;
import ru.nsu.ccfit.student_compass.repository.UserRepository;
import ru.nsu.ccfit.student_compass.utils.JpaUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    private final SubjectRepository subjectRepository;

    private final UserRepository userRepository;

    private final OfferRepository offerRepository;

    public TaskDto createTask(String title, String description, String startPrice, Long subjectId) {
        log.info("Create task: " + title);
        return TaskMapper.INSTANCE.toDto(
            taskRepository.save(
                new Task()
                    .setTitle(title)
                    .setStartPrice(startPrice)
                    .setDescription(description)
                    .setSubject(JpaUtils.findByIdOrThrow(subjectRepository, subjectId))
            )
        );
    }

    public List<TaskDto> getTasks() {
        log.info("Get all tasks");
        return taskRepository.findAll().stream()
            .map(TaskMapper.INSTANCE::toDto)
            .toList();
    }

    public OfferDto createOffer(Long taskId, Long userId, BigDecimal price) {
        log.info("Create offer taskId: " + taskId + " userID: " + userId);
        var task = JpaUtils.findByIdOrThrow(taskRepository, taskId);
        var offer = new Offer()
            .setPrice(price)
            .setUser(JpaUtils.findByIdOrThrow(userRepository, userId));
        offerRepository.save(offer);
        task.addOffer(offer);
        return OfferMapper.INSTANCE.toDto(offer);
    }

    public TaskDto closeTask(Long taskId) {
        return TaskMapper.INSTANCE.toDto(JpaUtils.findByIdOrThrow(taskRepository, taskId).closeTask());
    }

    public List<TaskDto> getFilteredTasks(Set<Long> subjectsId) {
        return taskRepository.findAllBySubjectIdIn(subjectsId).stream()
            .map(TaskMapper.INSTANCE::toDto)
            .toList();
    }
}
