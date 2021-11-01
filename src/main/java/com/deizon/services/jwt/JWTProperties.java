package com.deizon.services.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.time.Duration;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "auth")
public class JWTProperties {

    private final String privateKeySourcePath;

    private final String publicKeySourcePath;

    /** Name of the token issuer */
    private final String tokenIssuer;

    /** Duration after which a token will expire */
    private final Duration shortTokenExpiration = Duration.ofHours(2);

    /** Duration after which a token will expire */
    private final Duration tokenExpiration = Duration.ofDays(30);

}
