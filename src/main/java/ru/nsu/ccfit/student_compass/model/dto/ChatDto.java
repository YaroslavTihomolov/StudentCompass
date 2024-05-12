package ru.nsu.ccfit.student_compass.model.dto;

import lombok.Builder;

import java.util.Map;

@Builder
public record ChatDto(
    Long chatId,
    String name,
    Map<Long, String> users
) {
}
