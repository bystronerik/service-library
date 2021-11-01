package com.deizon.services.security;

import com.deizon.services.util.Base64Util;
import com.deizon.services.util.ResourceUtil;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;

@Component
public class PrivateKeyProvider extends BaseKeyProvider {

    public PrivateKeyProvider(ResourceUtil resourceUtil, Base64Util base64Util) {
        super(resourceUtil, base64Util);
    }

    public PrivateKey getPrivateKey(String resourcePath) {
        return this.readKey(
                resourcePath,
                this::privateKeySpec,
                this::privateKeyGenerator
        );
    }

}
