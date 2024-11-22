package org.example.springboot.ada2_0.Controller;

import lombok.RequiredArgsConstructor;
import org.example.springboot.ada2_0.Entity.Resource;
import org.example.springboot.ada2_0.Services.GroupHandler;
import org.example.springboot.ada2_0.Services.MinioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UploadController {

    private final MinioService minioService;

    @Autowired
    public UploadController(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("groupName") String groupName, @RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
        try {
            String uploadedFileName = minioService.uploadFile(file, fileName);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Файл успешно загружен: " + uploadedFileName); //Or use english here
            response.put("fileName", uploadedFileName);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Ошибка загрузки файла: " + e.getMessage()); //Or use english
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }




}

