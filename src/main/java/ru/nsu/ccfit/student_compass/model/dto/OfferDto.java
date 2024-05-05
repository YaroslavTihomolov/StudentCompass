package ru.nsu.ccfit.student_compass.model.dto;

import java.math.BigDecimal;

public record OfferDto(UserDto user, BigDecimal price) {
}
