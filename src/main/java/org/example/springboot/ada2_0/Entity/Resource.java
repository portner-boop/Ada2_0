package org.example.springboot.ada2_0.Entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Resource {
    private MultipartFile file;
}
