package ru.nsu.ccfit.student_compass.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nsu.ccfit.student_compass.model.dto.MessageResponseDto;
import ru.nsu.ccfit.student_compass.model.entity.Message;

@Mapper
public interface MessageMapper {
    @Mapping(target = "viewed", expression = "java(message.getViewedNames()")
    @Mapping(target = "created", expression = "java(message.getCreated().toString())")
    MessageResponseDto toDto(Message message);
}
