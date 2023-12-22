package ru.nsu.ccfit.student_compass.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.student_compass.model.dto.CourseDto;
import ru.nsu.ccfit.student_compass.model.dto.SubjectDto;
import ru.nsu.ccfit.student_compass.model.dto.SubjectInfoDto;
import ru.nsu.ccfit.student_compass.service.CourseService;
import ru.nsu.ccfit.student_compass.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping(value = "/student_compass")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping("/{university}/{course}")
    public ResponseEntity<List<SubjectDto>> getSubjects(@PathVariable String university, @PathVariable int course) {
        return ResponseEntity.ok(subjectService.getSubjects(university, course));
    }

    @GetMapping("/{university}/{course}/{subject}")
    public ResponseEntity<SubjectInfoDto> getSubjectInfo(@PathVariable String university,
                                                         @PathVariable int course, @PathVariable String subject) {
        return ResponseEntity.ok(subjectService.getSubjectInfo(university, course, subject));
    }

}
