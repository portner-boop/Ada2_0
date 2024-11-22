package org.example.springboot.ada2_0.Controller;

import lombok.RequiredArgsConstructor;
import org.example.springboot.ada2_0.Dto.ResourceDto;
import org.example.springboot.ada2_0.Entity.Resource;
import org.example.springboot.ada2_0.Mapping.ResourceMapper;
import org.example.springboot.ada2_0.Services.GroupHandler;
import org.example.springboot.ada2_0.Services.ResourceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ResourcesController {
    private final ResourceMapper resourceMapper;
    private final GroupHandler groupHandler;


    @PostMapping("/{group_id}/image")
    @PreAuthorize("canAccessTask(#group_id)")
    public void uploadImage(@PathVariable("group_id") int group_id, @ModelAttribute ResourceDto resourceDto,Model model) throws IOException {
        model.addAttribute("group_id", group_id);
        Resource resource = resourceMapper.toEntity(resourceDto);
        groupHandler.uploadResource(group_id,resource);
    }
}
