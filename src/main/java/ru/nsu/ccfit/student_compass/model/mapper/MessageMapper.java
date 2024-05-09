package ru.nsu.ccfit.student_compass.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.nsu.ccfit.student_compass.model.dto.MessageResponseDto;
import ru.nsu.ccfit.student_compass.model.entity.Message;

@Mapper
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(target = "viewed", expression = "java(userMessage.getViewedNames())")
    @Mapping(target = "created", expression = "java(userMessage.getCreated().toString())")
    MessageResponseDto toDto(Message userMessage);
}
