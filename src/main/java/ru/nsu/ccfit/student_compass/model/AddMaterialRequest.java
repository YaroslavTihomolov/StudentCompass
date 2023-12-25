package ru.nsu.ccfit.student_compass.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddMaterialRequest {

    private String author;
    private String name;
    private String link;

}
