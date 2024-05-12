package ru.nsu.ccfit.student_compass.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.nsu.ccfit.student_compass.model.dto.TaskDto;
import ru.nsu.ccfit.student_compass.model.entity.Task;

@Mapper(uses = {SubjectMapper.class, UserMapper.class})
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "subjectName.name", target = "subjectName")
    TaskDto toDto(Task task);

}
