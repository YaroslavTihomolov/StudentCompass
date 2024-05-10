package ru.nsu.ccfit.student_compass.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.student_compass.model.AuthenticationRequest;
import ru.nsu.ccfit.student_compass.model.AuthenticationResponse;
import ru.nsu.ccfit.student_compass.model.RegisterRequest;
import ru.nsu.ccfit.student_compass.service.AuthenticationService;

@RestController
@RequestMapping("/auth/student_compass")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        System.out.println(request);
        var token = service.register(request);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.ok(token);
    }

    @PostMapping("/authenticate")
    ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
