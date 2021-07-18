package com.deizon.services.model.file;

public enum FileType {
    IMAGE_PNG("image/png"),
    IMAGE_JPG("image/jpeg"),
    IMAGE_GIF("image/gif"),
    VIDEO_MP4("video/mp4"),
    VIDEO_AVI("video/x-msvideo"),
    VIDEO_MOV("video/quicktime"),
    VIDEO_WMV("video/x-ms-wmv");

    private final String name;

    FileType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
