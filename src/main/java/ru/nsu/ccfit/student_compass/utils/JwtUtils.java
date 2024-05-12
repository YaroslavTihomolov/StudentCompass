package ru.nsu.ccfit.student_compass.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import lombok.experimental.UtilityClass;
import ru.nsu.ccfit.student_compass.configuration.JwtService;

@UtilityClass
public class JwtUtils {
    public static String decodeJWT(String jwt) {
        byte[] keyBytes = Decoders.BASE64.decode(JwtService.KEY);
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(keyBytes)
            .build()
            .parseClaimsJws(jwt)
            .getBody();
        return claims.getSubject();
    }
}
