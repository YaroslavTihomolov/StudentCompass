package ru.nsu.ccfit.student_compass.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.student_compass.model.dto.OfferDto;
import ru.nsu.ccfit.student_compass.model.dto.SubjectDto;
import ru.nsu.ccfit.student_compass.model.dto.TaskDto;
import ru.nsu.ccfit.student_compass.model.entity.Offer;
import ru.nsu.ccfit.student_compass.model.entity.Task;
import ru.nsu.ccfit.student_compass.model.entity.User;
import ru.nsu.ccfit.student_compass.model.mapper.OfferMapper;
import ru.nsu.ccfit.student_compass.model.mapper.SubjectNameMapper;
import ru.nsu.ccfit.student_compass.model.mapper.TaskMapper;
import ru.nsu.ccfit.student_compass.repository.*;
import ru.nsu.ccfit.student_compass.utils.JwtUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
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

    public TaskDto createTask(String title, String description, String startPrice, String subjectName, String jwt) {
        User user = userRepository.findByEmail(JwtUtils.decodeJWT(jwt))
                .orElseThrow(EntityNotFoundException::new);

        log.info("Create task: " + title);
        Task task = taskRepository.save(
                new Task()
                        .setTitle(title)
                        .setStartPrice(startPrice)
                        .setDescription(description)
                        .setSubjectName(subjectNameRepository.findSubjectNameByName(subjectName))
                        .setUser(user)
        );

        return TaskDto.builder()
                .id(task.getId())
                .createByCurrentUser(true)
                .description(task.getDescription())
                .subjectName(task.getSubjectName().getName())
                .title(task.getTitle())
                .startPrice(task.getStartPrice())
                .build();
    }

    public List<TaskDto> getTasks(String jwt) {
        User user = userRepository.findByEmail(JwtUtils.decodeJWT(jwt))
                .orElseThrow(EntityNotFoundException::new);

        log.info("Get all tasks");
        return taskRepository.findAll().stream()
                .map(task -> TaskDto
                        .builder()
                        .id(task.getId())
                        .createByCurrentUser(Objects.equals(task.getUser().getId(), user.getId()))
                        .description(task.getDescription())
                        .subjectName(task.getSubjectName().getName())
                        .title(task.getTitle())
                        .startPrice(task.getStartPrice())
                        .build())
                .toList();
    }

    public OfferDto createOffer(Long taskId, BigDecimal price, String jwt) {
        log.info("Create offer taskId: " + taskId);
        var task = findByIdOrThrow(taskRepository, taskId);
        User user = userRepository.findByEmail(JwtUtils.decodeJWT(jwt))
                .orElseThrow(EntityNotFoundException::new);

        var offer = new Offer()
                .setPrice(price)
                .setTask(task)
                .setUser(user);
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

    public List<SubjectDto> getSubjects() {
        return subjectNameRepository.findAll().stream().map(SubjectNameMapper.INSTANCE::toDto).toList();
    }
}
