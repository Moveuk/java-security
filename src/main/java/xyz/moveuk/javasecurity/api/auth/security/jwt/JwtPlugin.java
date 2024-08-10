package xyz.moveuk.javasecurity.api.auth.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtPlugin {

    private final String issuer;
    private final String secret;
    private final long accessTokenExpirationHour;

    public JwtPlugin(
            @Value("${auth.jwt.issuer}") String issuer,
            @Value("${auth.jwt.secret}") String secret,
            @Value("${auth.jwt.accessTokenExpirationHour}") Long accessTokenExpirationHour) {
        this.issuer = issuer;
        this.secret = secret;
        this.accessTokenExpirationHour = accessTokenExpirationHour;
    }

    public Jws<Claims> validateToken(String jwt) throws JwtException, IllegalArgumentException {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt);
    }

    public String generateAccessToken(String subject, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return generateToken(subject, claims, Duration.ofHours(accessTokenExpirationHour));
    }

    private String generateToken(String subject, Map<String, Object> claims, Duration expirationPeriod) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(subject)
                .issuer(issuer)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expirationPeriod)))
                .claims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}