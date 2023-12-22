package ru.nsu.ccfit.student_compass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.student_compass.model.dto.SubjectDto;
import ru.nsu.ccfit.student_compass.model.dto.SubjectInfoDto;
import ru.nsu.ccfit.student_compass.model.entity.Course;
import ru.nsu.ccfit.student_compass.model.entity.University;
import ru.nsu.ccfit.student_compass.model.mapper.SubjectMapper;
import ru.nsu.ccfit.student_compass.repository.UniversityRepository;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    UniversityRepository universityRepository;

    public List<SubjectDto> getSubjects(String universityName, int courseNum) {
        var course = getCourse(universityName, courseNum);
        return course.getSubjects().stream()
                .map(SubjectMapper.INSTANCE::toDto)
                .toList();
    }

    public SubjectInfoDto getSubjectInfo(String universityName, int courseNum, String subjectName) {
        Course course = getCourse(universityName, courseNum);
        var subjects = course.getSubjects();
        var subject = subjects.stream()
                .filter(curSubject -> curSubject.getName().equals(subjectName))
                .findAny();
        return subject.map(SubjectMapper.INSTANCE::infoToDto).orElse(null);
    }

    private Course getCourse(String universityName, int courseNum) {
        University university = universityRepository.findByName(universityName);
        var courses = university.getCourses();
        var course = courses.stream()
                .filter(curCourse -> curCourse.getNumber() == courseNum)
                .findAny();
        return course.orElseThrow(RuntimeException::new);
    }

}
