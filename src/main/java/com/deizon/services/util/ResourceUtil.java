package com.deizon.services.util;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
@AllArgsConstructor
public class ResourceUtil {

    private final ResourceLoader resourceLoader;

    public String asString(String resourcePath) throws IOException {
        final Resource resource = resourceLoader.getResource(resourcePath);
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        }
    }
}
