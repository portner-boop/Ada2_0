package org.example.springboot.ada2_0.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ResourceDto {
    @NotNull(message = "Resource must be not null")
    private MultipartFile file;
}
