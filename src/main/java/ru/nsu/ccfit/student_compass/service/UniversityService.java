package ru.nsu.ccfit.student_compass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.student_compass.model.dto.UniversityDto;
import ru.nsu.ccfit.student_compass.model.mapper.UniversityMapper;
import ru.nsu.ccfit.student_compass.repository.UniversityRepository;

import java.util.List;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    public List<UniversityDto> getUniversities() {
        var universities = universityRepository.findAll();
        return universities.stream()
                .map(UniversityMapper.INSTANCE::toDto)
                .toList();
    }

}
