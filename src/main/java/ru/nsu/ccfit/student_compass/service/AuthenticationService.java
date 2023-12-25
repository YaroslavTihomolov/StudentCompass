package ru.nsu.ccfit.student_compass.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.student_compass.configuration.JwtService;
import ru.nsu.ccfit.student_compass.model.AuthenticationRequest;
import ru.nsu.ccfit.student_compass.model.AuthenticationResponse;
import ru.nsu.ccfit.student_compass.model.RegisterRequest;
import ru.nsu.ccfit.student_compass.model.entity.Role;
import ru.nsu.ccfit.student_compass.model.entity.User;
import ru.nsu.ccfit.student_compass.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    private final JwtService jwtService;

    private final AuthenticationManager manager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userExistCheck(request.getEmail())) {
            return null;
        }
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private boolean userExistCheck(String email) {
        return repository.findByEmail(email).isPresent();
    }
}
