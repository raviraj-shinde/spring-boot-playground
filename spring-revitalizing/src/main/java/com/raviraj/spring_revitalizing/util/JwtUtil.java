package com.raviraj.spring_revitalizing.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${jwt.secret.key}")
    private String key;

//    private SignatureAlgorithm signatureAlgorithm() {
//        return io.jsonwebtoken.SignatureAlgorithm.HS256; // or RS256, ES256, etc.
//    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 2))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String jwt, UserDetails userDetails) {
        return true;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        JwtParser parser = Jwts.parser()  // ✅ Yes, .parser() is back in 0.12.0+
                .verifyWith(Keys.hmacShaKeyFor(key.getBytes()))  // ✅ Replaces setSigningKey()
                .build();

        return parser.parseSignedClaims(token).getPayload();  // ✅ parseSignedClaims instead of parseClaimsJws
    }
}
