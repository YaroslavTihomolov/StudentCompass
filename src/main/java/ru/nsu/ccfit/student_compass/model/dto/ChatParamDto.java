package ru.nsu.ccfit.student_compass.model.dto;

public record ChatParamDto(
        Long chatId,
        int numberPage,
        int sizePage) {
    public ChatParamDto() {
        this(0L, 0, 10);
    }
}