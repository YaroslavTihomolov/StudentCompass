package ru.nsu.ccfit.student_compass.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.student_compass.model.dto.OfferDto;
import ru.nsu.ccfit.student_compass.model.dto.TaskDto;
import ru.nsu.ccfit.student_compass.service.TaskService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/student_compass")
public class TaskController {

    private final TaskService taskService;

    @PutMapping("/create_task")
    public ResponseEntity<TaskDto> createTask(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String startPrice,
            @RequestParam String subjectName,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String jwt = authorizationHeader.substring(7);
        return ResponseEntity.ok(taskService.createTask(title, description, startPrice, subjectName, jwt));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getTasks(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);
        return ResponseEntity.ok(taskService.getTasks(jwt));
    }

    @GetMapping("/filter_task")
    public ResponseEntity<List<TaskDto>> getTasks(@RequestParam Set<String> subjectNames) {
        return ResponseEntity.ok(taskService.getFilteredTasks(subjectNames));
    }

    @GetMapping("/close_task")
    public ResponseEntity<TaskDto> closeTask(@RequestParam Long taskId) {
        return ResponseEntity.ok(taskService.closeTask(taskId));
    }

    @PutMapping("/task/add_offer")
    public ResponseEntity<OfferDto> createOffer(
            @RequestParam Long taskId,
            @RequestParam BigDecimal price,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String jwt = authorizationHeader.substring(7);
        return ResponseEntity.ok(taskService.createOffer(taskId, price, jwt));
    }

    @GetMapping("/get_offers")
    public ResponseEntity<List<OfferDto>> getTaskOffers(@RequestParam Long taskId) {
        return ResponseEntity.ok(taskService.getOffers(taskId));
    }


}
