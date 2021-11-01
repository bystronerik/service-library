package com.deizon.services.scalar;

import com.deizon.services.model.FileUpload;
import graphql.schema.CoercingParseValueException;
import java.io.IOException;
import javax.servlet.http.Part;

public class FileUploadScalarCoercing extends InputOnlyScalarCoercing<FileUpload, Part> {

    public FileUploadScalarCoercing() {
        super(FileUpload.class, Part.class);
    }

    @Override
    protected FileUpload parseFromInput(Part input) {
        try {
            return new FileUpload(input.getContentType(), input.getSize(), input.getInputStream());
        } catch (IOException e) {
            throw new CoercingParseValueException("Couldn't read content of the uploaded file");
        }
    }
}
