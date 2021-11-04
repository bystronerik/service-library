package com.deizon.services.minio.service;

import com.deizon.services.model.FileUpload;
import io.minio.messages.Bucket;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface MinioService {

    boolean bucketExists(String bucketName);

    void makeBucket(String bucketName);

    List<String> listBucketName();

    List<Bucket> listBuckets();

    boolean removeBucket(String bucketName);

    List<String> listObjectNames(String bucketName);

    String putObject(MultipartFile multipartFile, String fileType, String bucketName);

    String putObject(String fileName, InputStream file, Long size, String fileType, String bucketName);

    String putObject(String fileName, FileUpload file, String bucketName);

    InputStream downloadObject(String bucketName, String objectName);

    boolean removeObject(String bucketName, String objectName);

    boolean removeListObject(String bucketName, List<String> objectNameList);

    String getObjectUrl(String bucketName, String objectName);
}
