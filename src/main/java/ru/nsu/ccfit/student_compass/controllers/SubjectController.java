package ru.nsu.ccfit.student_compass.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.student_compass.model.dto.SubjectDto;
import ru.nsu.ccfit.student_compass.model.dto.SubjectInfoDto;
import ru.nsu.ccfit.student_compass.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping(value = "/student_compass")
@CrossOrigin
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping("/{university}/{course}")
    public ResponseEntity<List<SubjectDto>> getSubjects(@PathVariable String university, @PathVariable String course) {
        return ResponseEntity.ok(subjectService.getSubjects(university, Integer.parseInt(course)));
    }

    @GetMapping("/{university}/{course}/{subjectId}")
    public ResponseEntity<SubjectInfoDto> getSubjectInfo(
            @PathVariable String university,
            @PathVariable String course,
            @PathVariable String subjectId) {
        return ResponseEntity.ok(subjectService.getSubjectInfo(university, Integer.parseInt(course), Integer.parseInt(subjectId)));
    }

}
