package org.example.springboot.ada2_0.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="groups")
@Data
public class Groups {


    @Id
    @Column(name="id")
    private int id;
    @Column(name="group_name")
    private String name;
    @Column(name="res")
    @CollectionTable(name="resources")
    @ElementCollection
    private List<String> resources ;

    public Groups() {}

    public Groups(int  id, String name) {
        this.id = id;
        this.name = name;
    }
    public void addResource(String  name){
        if (this.resources == null){
            this.resources = new ArrayList<>();
        }
        resources.add(name);
    }

}
