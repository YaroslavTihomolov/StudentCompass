package ru.nsu.ccfit.student_compass.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.nsu.ccfit.student_compass.model.dto.SubjectDto;
import ru.nsu.ccfit.student_compass.model.entity.SubjectName;

@Mapper
public interface SubjectNameMapper {
    SubjectNameMapper INSTANCE = Mappers.getMapper(SubjectNameMapper.class);

    SubjectDto toDto(SubjectName subject);

}
