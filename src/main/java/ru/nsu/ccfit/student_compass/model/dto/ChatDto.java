package ru.nsu.ccfit.student_compass.model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ChatDto(
        Long id,
        String name,
        List<String> users
) {
}
