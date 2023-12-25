package ru.nsu.ccfit.student_compass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.student_compass.model.dto.CourseDto;
import ru.nsu.ccfit.student_compass.model.mapper.CourseMapper;
import ru.nsu.ccfit.student_compass.repository.UniversityRepository;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    UniversityRepository universityRepository;

    public List<CourseDto> getCourses(String universityName) {
        var university = universityRepository.findByName(universityName);
        return university.getCourses().stream()
                .map(CourseMapper.INSTANCE::toDto)
                .toList();
    }

}
