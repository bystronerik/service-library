package com.deizon.services.util;

import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class Base64Util {
    public byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }
}
