package ru.nsu.ccfit.student_compass.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class SubjectInfoDto {

    private String name;

    private Set<ReviewDto> reviews;

    private Set<MaterialDto> materials;

}
