package ru.nsu.ccfit.student_compass.model.dto;

import jakarta.persistence.*;
import lombok.Data;
import ru.nsu.ccfit.student_compass.model.entity.Course;
import ru.nsu.ccfit.student_compass.model.entity.Material;
import ru.nsu.ccfit.student_compass.model.entity.Review;

import java.util.Set;

@Data
public class SubjectInfoDto {

    private String name;

    private Set<Review> reviews;

    private Set<Material> materials;

}
