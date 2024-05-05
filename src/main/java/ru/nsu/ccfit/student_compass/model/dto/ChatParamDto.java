package ru.nsu.ccfit.student_compass.model.dto;

import org.springframework.web.bind.annotation.RequestParam;

public record ChatParamDto(
    @RequestParam
    Long chatId,

    @RequestParam
    Long userId,

    @RequestParam(defaultValue = "1")
    int numberPage,
    @RequestParam(defaultValue = "10")
    int sizePage
) {
}
