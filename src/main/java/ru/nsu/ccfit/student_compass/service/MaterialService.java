package ru.nsu.ccfit.student_compass.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ccfit.student_compass.configuration.JwtService;
import ru.nsu.ccfit.student_compass.model.AddMaterialRequest;
import ru.nsu.ccfit.student_compass.model.dto.SubjectInfoDto;
import ru.nsu.ccfit.student_compass.model.entity.Material;
import ru.nsu.ccfit.student_compass.model.mapper.MaterialMapper;
import ru.nsu.ccfit.student_compass.repository.MaterialRepository;
import ru.nsu.ccfit.student_compass.repository.SubjectRepository;
import ru.nsu.ccfit.student_compass.repository.UserRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;

    private final UserRepository userRepository;

    private final SubjectRepository subjectRepository;

    @Transactional
    public SubjectInfoDto storeData(AddMaterialRequest request, String jwt, String subjectId) {
        String email = decodeJWT(jwt);
        var user = userRepository.findByEmail(email);
        var subject = subjectRepository.findById(Long.valueOf(subjectId));
        var material = Material.builder()
                .user(user.orElseThrow())
                .author(request.getAuthor())
                .name(request.getName())
                .link(request.getLink())
                .subject(subject.orElseThrow())
                .build();
        materialRepository.saveAndFlush(material);
        return SubjectInfoDto.builder()
                .materials(Set.of(MaterialMapper.INSTANCE.toDto(material)))
                .build();
    }

    private String decodeJWT(String jwt) {
        byte[] keyBytes = Decoders.BASE64.decode(JwtService.KEY);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(keyBytes)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims.getSubject();
    }
}
