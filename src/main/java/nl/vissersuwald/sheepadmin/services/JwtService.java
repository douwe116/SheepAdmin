package nl.vissersuwald.sheepadmin.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

    // In production, use a secure, random secret stored in env variables
    private static final String SECRET = "replace_this_with_secure_random_key";

    // Generate token for a username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)              // 'sub' claim
                .setIssuedAt(new Date())           // 'iat' claim
                .setExpiration(Date.from(
                        Instant.now().plus(1, ChronoUnit.HOURS))) // 'exp' claim
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract username from token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validate token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return username.equals(userDetails.getUsername()) && expiration.after(new Date());
    }
}
