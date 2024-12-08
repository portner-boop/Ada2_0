package org.example.springboot.ada2_0.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.example.springboot.ada2_0.Dao.ResourcesRepository;
import org.example.springboot.ada2_0.Dto.ResourceDto;
import org.example.springboot.ada2_0.Entity.Groups;
import org.example.springboot.ada2_0.Entity.Resource;
import org.example.springboot.ada2_0.Services.GroupHandler;
import org.example.springboot.ada2_0.Services.MinioService;

import org.example.springboot.ada2_0.Services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UploadController {

    private final MinioService minioService;
    private final GroupHandler groupHandler;
    private final ResourcesRepository resourcesRepository;
    private final ResourceService resourceService;

    @Autowired
    public UploadController(MinioService minioService, GroupHandler groupHandler, ResourcesRepository resourcesRepository, ResourceService resourceService) {
        this.minioService = minioService;
        this.groupHandler = groupHandler;
        this.resourcesRepository = resourcesRepository;
        this.resourceService = resourceService;
    }

    @PostMapping("/upload")
    @Transactional
    public ResponseEntity<Map<String, Object>> uploadFile(
            @RequestParam("groupName") String groupName,
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileName") String fileName,
            HttpSession session
    ) throws IOException {
        try {
            int groupId = (int) session.getAttribute("group_id");
            String uploadedFileName = minioService.uploadFile(file, fileName);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Файл успешно загружен: " + uploadedFileName);
            response.put("fileName", uploadedFileName);
            Groups group = groupHandler.getGroups(groupId);
            if (group == null) {
                throw new Exception("Group not found");
            }
            Resource resource = new Resource(fileName);
            resource.setGroups(group);
            resourceService.save(resource);
//            ResponseEntity<List<ResourceDto>> filesResponse = getFileUrls(session);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Ошибка загрузки файла: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResourceDto>> getFileUrls(HttpSession session) {
        try {
            int groupId = (int) session.getAttribute("group_id");
            Groups group = groupHandler.getGroups(groupId);
            List<Resource> resources = resourceService.findAllResources(group);
            System.out.println("Resources found for groupId " + groupId + ": " + resources);
            List<ResourceDto> fileUrls = new ArrayList<>();
            for (Resource resource : resources) {
                if (resource.getGroups() != null && resource.getFile() != null) {
                    String name = resource.getFile();
                    ResourceDto res = new ResourceDto(name,minioService.getPreviewFileUrl(name));
                    fileUrls.add(res);
                }
            }
            return ResponseEntity.ok(fileUrls);
        } catch (Exception e) {
            System.err.println("Error generating file URLs: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



}

