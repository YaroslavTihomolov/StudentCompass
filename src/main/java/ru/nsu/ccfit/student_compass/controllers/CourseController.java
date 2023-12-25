package ru.nsu.ccfit.student_compass.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.student_compass.model.dto.CourseDto;
import ru.nsu.ccfit.student_compass.service.CourseService;

import java.util.List;

@RestController
@RequestMapping(value = "/student_compass")
@CrossOrigin
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/{name}")
    public ResponseEntity<List<CourseDto>> getCourses(@PathVariable String name) {
        var a = courseService.getCourses(name);
        return ResponseEntity.ok(a);
    }

}
