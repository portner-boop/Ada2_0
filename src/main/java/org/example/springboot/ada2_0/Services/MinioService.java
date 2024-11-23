package org.example.springboot.ada2_0.Services;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.example.springboot.ada2_0.Props.MinioProperties;
import java.time.Duration;




import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class MinioService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;


    @Autowired
    public MinioService(MinioClient minioClient, MinioProperties minioProperties) {
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
    }


    public String uploadFile(MultipartFile file, String fileName) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        ensureBucketExists();
        String objectName = fileName;

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(minioProperties.getBucket())
                        .object(objectName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());
        return objectName;
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
            return "..."; // Or handle the exception appropriately
        }
    }
//public String loadUrlDocument(String name) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
//        String buckert = minioProperties.getBucket();
//        return minioClient.getPresignedObjectUrl(
//                GetPresignedObjectUrlArgs.builder()
//                        .bucket(buckert)
//                        .object(name)
//                        .build());
//    }
//public String getTemporaryAccessLink(String bucketName, String objectName, int expirySeconds) {
//    try {
//
//        AccessKey accessKey = minioClient.presignedGetObject(
//                PresignedGetObjectArgs.builder()
//                        .bucket(bucketName)
//                        .object(objectName)
//                        .expiry(Duration.ofSeconds(expirySeconds))
//                        .build());
//        // Construct the pre-signed URL for the GetObject action using temporary accessKey
//        String url = accessKey.url();
//        return url;
//    } catch (Exception e) {
//        System.err.println("Error generating temporary access link: " + e.getMessage());
//        e.printStackTrace();
//        return null; // Or throw an exception as appropriate
//    }
}
