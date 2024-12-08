package org.example.springboot.ada2_0.Dao;

import org.example.springboot.ada2_0.Entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {

    public Optional<MyUser> findByUsername(String username);
    public Optional<MyUser> findByGroup(int group);
}
