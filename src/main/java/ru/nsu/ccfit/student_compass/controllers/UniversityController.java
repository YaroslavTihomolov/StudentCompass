package ru.nsu.ccfit.student_compass.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.student_compass.model.dto.UniversityDto;
import ru.nsu.ccfit.student_compass.service.UniversityService;

import java.util.List;

@RestController
@RequestMapping(value = "/student_compass")
public class UniversityController {

    @Autowired
    UniversityService universityService;

    @GetMapping
    public ResponseEntity<List<UniversityDto>> getUniversities() {
        return ResponseEntity.ok(universityService.getUniversities());
    }

}
