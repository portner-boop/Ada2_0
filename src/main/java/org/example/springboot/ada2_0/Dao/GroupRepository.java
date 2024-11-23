package org.example.springboot.ada2_0.Dao;


import org.example.springboot.ada2_0.Entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Groups,Integer> {
    public Optional<Groups> findByName(String name);
    public Groups findById(int id);
}
