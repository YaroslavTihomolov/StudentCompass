package ru.nsu.ccfit.student_compass.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.student_compass.model.dto.UniversityDto;
import ru.nsu.ccfit.student_compass.service.UniversityService;

import java.util.List;

@RestController
@RequestMapping(value = "/student_compass")
@Slf4j
@CrossOrigin
public class UniversityController {

    @Autowired
    UniversityService universityService;

    @GetMapping
    public ResponseEntity<List<UniversityDto>> getUniversities() {
        log.info("Get university request");
        return ResponseEntity.ok(universityService.getUniversities());
    }

}
