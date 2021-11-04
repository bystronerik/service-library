package com.deizon.services.minio.service;

import com.deizon.services.minio.config.MinioProperties;
import com.deizon.services.minio.utils.MinioUtil;
import com.deizon.services.model.FileUpload;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    private final MinioUtil minioUtil;
    private final MinioProperties minioProperties;

    @Override
    public boolean bucketExists(String bucketName) {
        return minioUtil.bucketExists(bucketName);
    }

    @Override
    public void makeBucket(String bucketName) {
        minioUtil.makeBucket(bucketName);
    }

    @Override
    public List<String> listBucketName() {
        return minioUtil.listBucketNames();
    }

    @Override
    public List<Bucket> listBuckets() {
        return minioUtil.listBuckets();
    }

    @Override
    public boolean removeBucket(String bucketName) {
        return minioUtil.removeBucket(bucketName);
    }

    @Override
    public List<String> listObjectNames(String bucketName) {
        return minioUtil.listObjectNames(bucketName);
    }

    @Override
    public String putObject(MultipartFile file, String fileType, String bucketName) {
        try {
            bucketName =
                    StringUtils.isNotBlank(bucketName)
                            ? bucketName
                            : minioProperties.getBucketName();
            if (!this.bucketExists(bucketName)) {
                this.makeBucket(bucketName);
            }
            final String fileName = file.getOriginalFilename();

            final String objectName =
                    UUID.randomUUID().toString().replaceAll("-", "")
                            + fileName.substring(fileName.lastIndexOf("."));
            minioUtil.putObject(bucketName, file, objectName, fileType);
            return minioProperties.getEndpoint() + "/" + bucketName + "/" + objectName;
        } catch (Exception e) {
            e.printStackTrace();
            return " Upload failed ";
        }
    }

    @Override
    public String putObject(String fileName, InputStream file, Long size, String fileType, String bucketName) {
        try {
            bucketName =
                    StringUtils.isNotBlank(bucketName)
                            ? bucketName
                            : minioProperties.getBucketName();
            if (!this.bucketExists(bucketName)) {
                this.makeBucket(bucketName);
            }

            final String objectName =
                    UUID.randomUUID().toString().replaceAll("-", "")
                            + fileName.substring(fileName.lastIndexOf("."));
            minioUtil.putObject(bucketName, file, objectName, size, fileType);
            return minioProperties.getEndpoint() + "/" + bucketName + "/" + objectName;
        } catch (Exception e) {
            e.printStackTrace();
            return " Upload failed ";
        }
    }

    @Override
    public String putObject(String fileName, FileUpload file, String bucketName) {
        return this.putObject(fileName, file.getContent(), file.getSize(), file.getContentType(), bucketName);
    }

    @Override
    public InputStream downloadObject(String bucketName, String objectName) {
        return minioUtil.getObject(bucketName, objectName);
    }

    @Override
    public boolean removeObject(String bucketName, String objectName) {
        return minioUtil.removeObject(bucketName, objectName);
    }

    @Override
    public boolean removeListObject(String bucketName, List<String> objectNameList) {
        return minioUtil.removeObject(bucketName, objectNameList);
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName) {
        return minioUtil.getObjectUrl(bucketName, objectName);
    }
}
