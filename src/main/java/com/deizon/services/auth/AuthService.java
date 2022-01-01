package com.deizon.services.auth;

import com.deizon.services.exception.BadTokenException;
import com.deizon.services.jwt.JWTProperties;
import com.deizon.services.jwt.JWTUserDetails;
import com.deizon.services.security.PrivateKeyProvider;
import com.deizon.services.security.PublicKeyProvider;
import io.jsonwebtoken.*;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AuthService {

    private final JWTProperties config;
    private final PrivateKeyProvider privateKeyProvider;
    private final PublicKeyProvider publicKeyProvider;

    @SuppressWarnings("unchecked")
    @Transactional
    public JWTUserDetails loadUserByToken(String token) {
        final Jws<Claims> decoded = getDecodedToken(token);
        final Claims data = decoded.getBody();
        return JWTUserDetails.builder()
                .username(data.getSubject())
                .token(token)
                .authorities((List<GrantedAuthority>) data.get("permissions"))
                .build();
    }

    public String generateToken(JWTUserDetails details, Instant expiration) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("permissions", details.getAuthorities());

        final JwtBuilder builder =
                Jwts.builder()
                        .setSubject(details.getUsername())
                        .setClaims(claims)
                        .setIssuedAt(Date.from(Instant.now()))
                        .setExpiration(Date.from(expiration))
                        .signWith(
                                this.privateKeyProvider.getPrivateKey(
                                        this.config.getPrivateKeySourcePath()),
                                SignatureAlgorithm.RS256);
        return builder.compact();
    }

    protected Jws<Claims> getDecodedToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(
                            this.publicKeyProvider.getPublicKey(
                                    this.config.getPublicKeySourcePath()))
                    .build()
                    .parseClaimsJws(token);
        } catch (JwtException ex) {
            throw new BadTokenException();
        }
    }
}
