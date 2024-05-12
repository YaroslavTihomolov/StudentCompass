package ru.nsu.ccfit.student_compass.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.nsu.ccfit.student_compass.model.dto.MaterialDto;
import ru.nsu.ccfit.student_compass.model.entity.Material;

@Mapper
public interface MaterialMapper {

    MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

    MaterialDto toDto(Material material);
}
