package org.example.springboot.ada2_0.Dao;

import org.example.springboot.ada2_0.Entity.Groups;
import org.example.springboot.ada2_0.Entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourcesRepository extends JpaRepository<Resource, Integer> {

    public List<Resource> getResourcesByGroups(Groups group);

}
