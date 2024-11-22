package org.example.springboot.ada2_0.Props;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "minio")
@Data

public class MinioProperties {
    private String bucket ;
    private String  url;
    private String accessKey;
    private String secretKey;

}
