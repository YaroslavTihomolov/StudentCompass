package ru.nsu.ccfit.student_compass.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.nsu.ccfit.student_compass.model.dto.UserDto;
import ru.nsu.ccfit.student_compass.model.entity.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);

}
