package xyz.moveuk.javasecurity.api.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtPluginTest {

    private JwtPlugin jwtPlugin;
    private String issuer = "testIssuer";
    private String secret = "ThisIsRandomStringForJwtSecretKeyTEST"; // 256-bit key
    private long accessTokenExpirationHour = 168L;

    @BeforeEach
    public void setUp() {
        jwtPlugin = new JwtPlugin(issuer, secret, accessTokenExpirationHour);
    }

    @Test
    public void testGenerateAccessToken() {
        //given
        String subject = "testUser";
        String role = "USER";

        //when
        String token = jwtPlugin.generateAccessToken(subject, role);

        assertNotNull(token);
        assertTrue(token.startsWith("eyJhbGciOiJIUzI1NiJ9"));

        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload();

        //then
        assertEquals(subject, claims.getSubject());
        assertEquals(role, claims.get("role"));
        assertEquals(issuer, claims.getIssuer());
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiration());
        assertTrue(claims.getExpiration().after(Date.from(Instant.now())));
    }
}