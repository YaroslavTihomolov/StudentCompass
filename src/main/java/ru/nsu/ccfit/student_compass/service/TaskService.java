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
import ru.nsu.ccfit.student_compass.repository.*;

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

    private final SubjectNameRepository subjectNameRepository;

    public TaskDto createTask(String title, String description, String startPrice, String subjectName) {
        log.info("Create task: " + title);
        return TaskMapper.INSTANCE.toDto(
                taskRepository.save(
                        new Task()
                                .setTitle(title)
                                .setStartPrice(startPrice)
                                .setDescription(description)
                                .setSubjectName(subjectNameRepository.findSubjectNameByName(subjectName))
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
        var task = findByIdOrThrow(taskRepository, taskId);
        var offer = new Offer()
                .setPrice(price)
                .setUser(findByIdOrThrow(userRepository, userId));
        offerRepository.save(offer);
        task.addOffer(offer);
        return OfferMapper.INSTANCE.toDto(offer);
    }

    public TaskDto closeTask(Long taskId) {
        return TaskMapper.INSTANCE.toDto(findByIdOrThrow(taskRepository, taskId).closeTask());
    }

    public List<TaskDto> getFilteredTasks(Set<String> subjectNames) {
        return taskRepository.findAllBySubjectNameNameIn(subjectNames).stream()
                .map(TaskMapper.INSTANCE::toDto)
                .toList();
    }

    private <T> T findByIdOrThrow(JpaRepository<T, Long> repository, Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<OfferDto> getOffers(Long taskId) {
        return offerRepository.findAllByTaskId(taskId).stream()
                .map(OfferMapper.INSTANCE::toDto)
                .toList();
    }
}
