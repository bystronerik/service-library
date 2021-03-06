package com.deizon.services.security;

import com.deizon.services.util.Base64Util;
import com.deizon.services.util.ResourceUtil;
import java.security.PublicKey;
import org.springframework.stereotype.Component;

@Component
public class PublicKeyProvider extends BaseKeyProvider {

    public PublicKeyProvider(ResourceUtil resourceUtil, Base64Util base64Util) {
        super(resourceUtil, base64Util);
    }

    public PublicKey getPublicKey(String resourcePath) {
        return this.readKey(resourcePath, this::publicKeySpec, this::publicKeyGenerator);
    }
}
