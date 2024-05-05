package ru.nsu.ccfit.student_compass.model.dto;

import java.util.Set;

public record CreateChatDto(
    String name,
    Set<Long> userIds
) {
}
