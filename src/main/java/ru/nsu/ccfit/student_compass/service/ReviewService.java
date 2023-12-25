package ru.nsu.ccfit.student_compass.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ccfit.student_compass.configuration.JwtService;
import ru.nsu.ccfit.student_compass.model.dto.SubjectInfoDto;
import ru.nsu.ccfit.student_compass.model.entity.Review;
import ru.nsu.ccfit.student_compass.model.mapper.ReviewMapper;
import ru.nsu.ccfit.student_compass.repository.ReviewRepository;
import ru.nsu.ccfit.student_compass.repository.SubjectRepository;
import ru.nsu.ccfit.student_compass.repository.UserRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final SubjectRepository subjectRepository;

    @Transactional
    public SubjectInfoDto storeReview(String text, String jwt, String subjectId) {
        String email = decodeJWT(jwt);
        var user = userRepository.findByEmail(email);
        var subject = subjectRepository.findById(Long.valueOf(subjectId));
        var review = Review.builder()
                .user(user.orElseThrow())
                .value(text)
                .subject(subject.orElseThrow())
                .build();
        reviewRepository.saveAndFlush(review);
        return SubjectInfoDto.builder()
                .reviews(Set.of(ReviewMapper.INSTANCE.toDto(review)))
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
