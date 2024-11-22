package org.example.springboot.ada2_0.Services;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.springboot.ada2_0.Entity.Resource;
import org.example.springboot.ada2_0.Props.MinioProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResourceService {
    private final MinioClient minioClient;
    @Autowired
    private MinioProperties minioProperties;

    public String upload(Resource resource) throws IOException {
        createBucket();
        MultipartFile file = resource.getFile();
        if(file.isEmpty() || file.getOriginalFilename()==null){
            return null;
        }

        String fileName = generateFileName(file);
        InputStream inputStream  =file.getInputStream();
        saveImage(inputStream,fileName);
        return fileName;
    }
    @SneakyThrows
    private void createBucket() {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(minioProperties.getBucket()).build());
        if(!found){
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .build());
        }
    }
    private String generateFileName(MultipartFile file) {
        String extension = getExtension(file);
        return UUID.randomUUID()+"."+extension;
    }
    private String getExtension(MultipartFile file) {
        return  file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf(".")+1);
    }
    @SneakyThrows
    private void saveImage(InputStream inputStream, String fileName)  {
        minioClient.putObject(PutObjectArgs.builder()
                .stream(inputStream,inputStream.available(),-1)
                        .bucket(minioProperties.getBucket())
                        .object(fileName)
                .build());
    }


}
