package com.deizon.services.util;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Base64Util {
    public byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }
}
