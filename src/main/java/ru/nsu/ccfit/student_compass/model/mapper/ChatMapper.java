package ru.nsu.ccfit.student_compass.model.mapper;

import jakarta.annotation.Nonnull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.nsu.ccfit.student_compass.model.dto.ChatDto;
import ru.nsu.ccfit.student_compass.model.entity.Chat;

import java.util.List;

@Mapper
public interface ChatMapper {

    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    @Mapping(target = "users", expression = "java(getUsers(chat))")
    ChatDto toDto(Chat chat);

    @Nonnull
    default List<String> getUsers(Chat chat) {
        return chat.getUser().stream().map(value -> "%s %s".formatted(value.getFirstName(), value.getLastName())).toList();

    }
}
