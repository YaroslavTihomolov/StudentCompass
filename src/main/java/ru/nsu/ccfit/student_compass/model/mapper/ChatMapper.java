package ru.nsu.ccfit.student_compass.model.mapper;

import jakarta.annotation.Nonnull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.nsu.ccfit.student_compass.model.dto.ChatDto;
import ru.nsu.ccfit.student_compass.model.entity.Chat;
import ru.nsu.ccfit.student_compass.model.entity.User;

import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface ChatMapper {

    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    @Mapping(target = "users", expression = "java(getUsers(chat))")
    ChatDto toDto(Chat chat);

    @Nonnull
    default Map<Long, String> getUsers(Chat chat) {
        return chat.getUser().stream().collect(Collectors.toMap(
                User::getId,
                value -> "%s %s".formatted(value.getFirstName(), value.getLastName())
            )
        );
    }
}
