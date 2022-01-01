package com.deizon.services.jwt;

import com.deizon.services.security.PrivateKeyProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTService {

    private final JWTProperties config;
    private final PrivateKeyProvider keyProvider;

    public String generateToken(String username) {
        Instant now = Instant.now();
        Instant expiry = Instant.now().plus(this.config.getTokenExpiration());

        return Jwts.builder()
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiry))
                .setIssuer(this.config.getTokenIssuer())
                .signWith(
                        this.keyProvider.getPrivateKey(this.config.getPrivateKeySourcePath()),
                        SignatureAlgorithm.RS256)
                .claim("username", username)
                .compact();
    }
}
