package ru.nsu.ccfit.student_compass.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.nsu.ccfit.student_compass.model.dto.UniversityDto;
import ru.nsu.ccfit.student_compass.model.entity.University;

@Mapper(uses = {CourseMapper.class})
public interface UniversityMapper {

    UniversityMapper INSTANCE = Mappers.getMapper(UniversityMapper.class);

    UniversityDto toDto(University subject);

}
