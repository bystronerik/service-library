/* Copyright: Erik Bystroň - Redistribution and any changes prohibited. */
package com.deizon.services.model;

import java.io.InputStream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.MediaType;

@Getter
@AllArgsConstructor
public class FileUpload {

    private final String contentType;
    private final Long size;
    private final InputStream content;

    public MediaType getMediaType() {
        return MediaType.parseMediaType(contentType);
    }
}
