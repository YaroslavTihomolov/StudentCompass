package ru.nsu.ccfit.student_compass.model.dto;

import java.util.Set;

public record MessageResponseDto(
    String userName,
    String text,
    String created,
    Set<String> viewed
) {
}
