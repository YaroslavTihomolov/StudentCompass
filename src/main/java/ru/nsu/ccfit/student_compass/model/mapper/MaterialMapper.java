package ru.nsu.ccfit.student_compass.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.nsu.ccfit.student_compass.model.dto.MaterialDto;
import ru.nsu.ccfit.student_compass.model.entity.Material;

@Mapper
public interface MaterialMapper {

    MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

    @Mapping(source = "subject.id", target = "subjectId")
    MaterialDto toDto(Material material);


}
