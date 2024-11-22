package org.example.springboot.ada2_0.Services;

import org.example.springboot.ada2_0.Dao.GroupRepository;
import org.example.springboot.ada2_0.Dto.RegistryUser;
import org.example.springboot.ada2_0.Entity.Groups;
import org.example.springboot.ada2_0.Entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class GroupHandler {
    @Autowired
    private GroupRepository groupRepository;


    //    public String validateGroup(RegistryUser registryUser) {
//        if(groupRepository.findByName(registryUser.getPg_password()).isPresent()){
//            return registryUser.getPg_password();
//        }else{
//            return null;
//        }
//    }
    public Optional<Integer> getGroupId(RegistryUser registryUser) {
        return groupRepository.findByName(registryUser.getPg_password())
                .map(Groups::getId);
    }



}
