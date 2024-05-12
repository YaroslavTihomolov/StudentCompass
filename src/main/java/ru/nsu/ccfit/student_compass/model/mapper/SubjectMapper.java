package ru.nsu.ccfit.student_compass.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.nsu.ccfit.student_compass.model.dto.SubjectDto;
import ru.nsu.ccfit.student_compass.model.dto.SubjectInfoDto;
import ru.nsu.ccfit.student_compass.model.entity.Subject;

@Mapper(uses = {ReviewMapper.class, MaterialMapper.class})
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);
    @Mapping(target = "name", expression = "java(subject.getSubjectName().getName())")
    SubjectDto toDto(Subject subject);

    @Mapping(source = "materials", target = "materials")
    @Mapping(source = "reviews", target = "reviews")
    SubjectInfoDto infoToDto(Subject subject);
}
