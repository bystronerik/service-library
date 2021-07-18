package com.deizon.services.scalar;

import com.deizon.services.model.file.FileType;

public class FileTypeScalarCoercing extends EnumScalarCoercing<FileType> {

    public FileTypeScalarCoercing() {
        super(FileType.class);
    }
}
