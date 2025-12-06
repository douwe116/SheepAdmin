package nl.vissersuwald.sheepadmin.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    // Keep this in sync with JwtService.SECRET
    private static final String SECRET = "replace_this_with_secure_random_key";

    private final JwtService jwtService = new JwtService();

    @Test
    void generateToken_and_extractUsername_shouldWork() {
        String username = "testuser";

        String token = jwtService.generateToken(username);
        String extractedUsername = jwtService.extractUsername(token);

        assertNotNull(token);
        assertFalse(token.isBlank());
        assertEquals(username, extractedUsername);
    }

    @Test
    void isTokenValid_shouldReturnTrue_forValidTokenAndUser() {
        String username = "validuser";
        UserDetails userDetails = new User(username, "password", Collections.emptyList());

        String token = jwtService.generateToken(username);
        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    @Test
    void isTokenValid_shouldReturnFalse_forTokenWithDifferentUsername() {
        String tokenUsername = "tokenUser";
        String otherUsername = "otherUser";
        UserDetails userDetails = new User(otherUsername, "password", Collections.emptyList());

        String token = jwtService.generateToken(tokenUsername);
        assertFalse(jwtService.isTokenValid(token, userDetails));
    }

    @Test
    void isTokenValid_shouldReturnFalse_forExpiredToken() {
        String username = "expiredUser";
        UserDetails userDetails = new User(username, "password", Collections.emptyList());

        // create an already-expired token using the same secret & algorithm
        String expiredToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now().minus(2, ChronoUnit.HOURS)))
                .setExpiration(Date.from(Instant.now().minus(1, ChronoUnit.HOURS)))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();

        boolean isValid = jwtService.isTokenValid(expiredToken, userDetails);

        // then
        assertFalse(isValid);
    }
}
