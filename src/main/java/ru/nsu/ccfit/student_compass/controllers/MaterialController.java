package ru.nsu.ccfit.student_compass.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.student_compass.model.AddMaterialRequest;
import ru.nsu.ccfit.student_compass.model.dto.SubjectInfoDto;
import ru.nsu.ccfit.student_compass.service.MaterialService;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService service;

    @PostMapping("/material/{subjectId}")
    public ResponseEntity<SubjectInfoDto> postReview(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AddMaterialRequest request,
            @PathVariable String subjectId) {
        String jwt = authorizationHeader.substring(7);
        return ResponseEntity.ok(service.storeData(request, jwt, subjectId));
    }

}
