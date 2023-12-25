package ru.nsu.ccfit.student_compass.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.student_compass.model.ReviewRequest;
import ru.nsu.ccfit.student_compass.model.dto.SubjectInfoDto;
import ru.nsu.ccfit.student_compass.service.ReviewService;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @PostMapping("/review/{subjectId}")
    public ResponseEntity<SubjectInfoDto> postReview(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody ReviewRequest reviewRequest,
            @PathVariable String subjectId) {
        String jwt = authorizationHeader.substring(7);
        return ResponseEntity.ok(service.storeReview(reviewRequest.getText(), jwt, subjectId));
    }

}
