package org.example.springboot.ada2_0.Services;

import jakarta.transaction.Transactional;
import org.example.springboot.ada2_0.Dao.ResourcesRepository;
import org.example.springboot.ada2_0.Entity.Groups;
import org.example.springboot.ada2_0.Entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    @Autowired
    private ResourcesRepository resourcesRepository;
    @Transactional
    public void save(Resource resources) {
        resourcesRepository.save(resources);
    }
    @Transactional
    public List<Resource> findAllResources(Groups group) {
        return resourcesRepository.getResourcesByGroups(group);
    }
}
