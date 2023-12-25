package ru.nsu.ccfit.student_compass.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String token;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
