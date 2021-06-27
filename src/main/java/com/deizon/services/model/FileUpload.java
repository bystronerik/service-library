/* Copyright: Erik Bystroň - Redistribution and any changes prohibited. */
package com.deizon.services.model;

import java.io.InputStream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileUpload {

    private final String contentType;
    private final InputStream content;
}
