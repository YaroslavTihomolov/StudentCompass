package ru.nsu.ccfit.student_compass.model.dto;

import lombok.Builder;

@Builder
public record TaskDto(Long id, String title, String description, String startPrice, String subjectName, boolean createByCurrentUser, boolean isClose) {
}
