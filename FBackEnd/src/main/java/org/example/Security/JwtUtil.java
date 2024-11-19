package org.example.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component // Adnotare care permite Spring sa gestioneze această clasa ca Bean
public class JwtUtil {
    private final String secretKey = "AceastaEsteOCheieFoarteLungaSiSigura12345"; // Cheie secreta(necesar 32 caractere)
    private final int expirationTime = 3600000; // 1 ora

    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return claims.getBody().getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Invalid JWT token");
            return null;
        }
    }
}
