package ru.nsu.ccfit.student_compass.model.dto;

public record MessageResponseDto(
    String userName,
    String text,
    String created
) {
}
