package com.deizon.services.security;

import com.deizon.services.exception.JwtInitializationException;
import com.deizon.services.util.Base64Util;
import com.deizon.services.util.ResourceUtil;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.function.BiFunction;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class BaseKeyProvider {

    private final ResourceUtil resourceUtil;
    private final Base64Util base64Util;

    protected  <T extends Key> T readKey(String resourcePath, Function<String, EncodedKeySpec> keySpec, BiFunction<KeyFactory, EncodedKeySpec, T> keyGenerator) {
        try {
            return keyGenerator.apply(KeyFactory.getInstance("RSA"), keySpec.apply(resourceUtil.asString(resourcePath)));
        } catch(NoSuchAlgorithmException | IOException e) {
            throw new JwtInitializationException(e);
        }
    }

    protected EncodedKeySpec publicKeySpec(String data) {
        return new X509EncodedKeySpec(base64Util.decode(data));
    }


    protected PublicKey publicKeyGenerator(KeyFactory kf, EncodedKeySpec spec) {
        try {
            return kf.generatePublic(spec);
        } catch(InvalidKeySpecException e) {
            throw new JwtInitializationException(e);
        }
    }

    protected EncodedKeySpec privateKeySpec(String data) {
        return new PKCS8EncodedKeySpec(base64Util.decode(data));
    }

    protected PrivateKey privateKeyGenerator(KeyFactory kf, EncodedKeySpec spec) {
        try {
            return kf.generatePrivate(spec);
        } catch(InvalidKeySpecException e) {
            throw new JwtInitializationException(e);
        }
    }

}
