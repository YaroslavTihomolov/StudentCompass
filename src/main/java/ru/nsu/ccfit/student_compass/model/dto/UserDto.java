package ru.nsu.ccfit.student_compass.model.dto;

import lombok.Builder;

@Builder
public record UserDto(Long id, String firstName, String lastName) {

}
