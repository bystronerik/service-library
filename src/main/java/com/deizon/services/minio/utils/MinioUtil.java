package com.deizon.services.minio.utils;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class MinioUtil {

    private final MinioClient minioClient;

    @SneakyThrows
    public boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    @SneakyThrows
    public boolean makeBucket(String bucketName) {
        if (!bucketExists(bucketName)) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());

            return true;
        } else {
            return false;
        }
    }

    @SneakyThrows
    public List<String> listBucketNames() {
        final List<String> bucketListName = new ArrayList<>();
        for (Bucket bucket : listBuckets()) {
            bucketListName.add(bucket.name());
        }
        return bucketListName;
    }

    @SneakyThrows
    public List<Bucket> listBuckets() {
        return minioClient.listBuckets();
    }

    @SneakyThrows
    public boolean removeBucket(String bucketName) {
        if (bucketExists(bucketName)) {
            for (Result<Item> result : listObjects(bucketName)) {
                if (result.get().size() > 0) {
                    return false;
                }
            }
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
            return !bucketExists(bucketName);
        }
        return false;
    }

    @SneakyThrows
    public List<String> listObjectNames(String bucketName) {
        final List<String> listObjectNames = new ArrayList<>();
        if (bucketExists(bucketName)) {
            for (Result<Item> result : listObjects(bucketName)) {
                listObjectNames.add(result.get().objectName());
            }
        }

        return listObjectNames;
    }

    @SneakyThrows
    public Iterable<Result<Item>> listObjects(String bucketName) {
        if (bucketExists(bucketName)) {
            return minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
        }
        return null;
    }

    @SneakyThrows
    public void putObject(
            String bucketName, MultipartFile multipartFile, String filename, String fileType) {
        final InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes());
        minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(filename).stream(
                                inputStream, multipartFile.getSize(), -1)
                        .contentType(fileType)
                        .build());
    }

    @SneakyThrows
    public String getObjectUrl(String bucketName, String objectName) {
        if (bucketExists(bucketName)) {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectName)
                            .expiry(2, TimeUnit.MINUTES)
                            .build());
        }
        return null;
    }

    @SneakyThrows
    public boolean removeObject(String bucketName, String objectName) {
        if (bucketExists(bucketName)) {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
            return true;
        }
        return false;
    }

    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName) {
        if (bucketExists(bucketName)) {
            final StatObjectResponse statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.size() > 0) {
                return minioClient.getObject(
                        GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
            }
        }
        return null;
    }

    @SneakyThrows
    public StatObjectResponse statObject(String bucketName, String objectName) {
        if (bucketExists(bucketName)) {
            return minioClient.statObject(
                    StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
        }
        return null;
    }

    @SneakyThrows
    public boolean removeObject(String bucketName, List<String> objectNames) {
        if (bucketExists(bucketName)) {
            final List<DeleteObject> objects = new LinkedList<>();
            for (String objectName : objectNames) {
                objects.add(new DeleteObject(objectName));
            }
            final Iterable<Result<DeleteError>> results =
                    minioClient.removeObjects(
                            RemoveObjectsArgs.builder()
                                    .bucket(bucketName)
                                    .objects(objects)
                                    .build());
            for (Result<DeleteError> result : results) {
                final DeleteError error = result.get();
                System.out.println(
                        "Error in deleting object " + error.objectName() + "; " + error.message());
                return false;
            }
        }
        return true;
    }

    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName, long offset, Long length) {
        if (bucketExists(bucketName)) {
            final StatObjectResponse statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.size() > 0) {
                return minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket(bucketName)
                                .object(objectName)
                                .offset(offset)
                                .length(length)
                                .build());
            }
        }
        return null;
    }

    @SneakyThrows
    public boolean putObject(
            String bucketName,
            InputStream inputStream,
            String objectName,
            Long size,
            String contentType) {
        if (bucketExists(bucketName)) {
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                                    inputStream, size, -1)
                            .contentType(contentType)
                            .build());
            final StatObjectResponse statObject = statObject(bucketName, objectName);
            return statObject != null && statObject.size() > 0;
        }
        return false;
    }
}
