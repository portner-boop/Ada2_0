package org.example.springboot.ada2_0.Services;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.AllArgsConstructor;
import org.example.springboot.ada2_0.Entity.Groups;
import org.example.springboot.ada2_0.Entity.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.example.springboot.ada2_0.Props.MinioProperties;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class MinioService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;
    private final ResourceService resourceService;
    private final GroupHandler groupHandler;


    public String uploadFile(MultipartFile file, String fileName) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        ensureBucketExists();
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(minioProperties.getBucket())
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());
        return fileName;
    }
    private void ensureBucketExists() {
        try {
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucket()).build());
            if (!exists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucket()).build());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error creating MinIO bucket", e);
        }
    }
    private static final Logger logger = LoggerFactory.getLogger(MinioService.class);
    public String getPreviewFileUrl(String fileName) {
        String bucketName = minioProperties.getBucket();
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
        } catch (Exception e) {
            logger.error("Error generating presigned URL: ", e);
            return "...";
        }
    }
    public void saveDataOfFile(String fileName, int groupId) throws Exception {
        Groups group = groupHandler.getGroups(groupId);
        if (group == null) {
            throw new Exception("Group not found");
        }
        Resource resource = new Resource(fileName);
        resource.setGroups(group);
        resourceService.save(resource);
    }

}
