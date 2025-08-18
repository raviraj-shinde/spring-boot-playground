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

    //Just This method enough to Generate JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 2))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }


    /******************************************************************************/

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //authenticate, i.e verify and give claims (parse and validate)
    private Claims extractAllClaims(String token) {
        JwtParser parser = Jwts.parser()  // ✅ Yes, .parser() is back in 0.12.0+ //gets a JwtParserBuilder
                .verifyWith(Keys.hmacShaKeyFor(key.getBytes()))  // ✅ Replaces setSigningKey()
                .build(); // now gets a JwtParser
        System.out.println(parser.parseSignedClaims(token).getPayload());
        return parser.parseSignedClaims(token).getPayload();  // ✅ parseSignedClaims instead of parseClaimsJws
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        return (extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
