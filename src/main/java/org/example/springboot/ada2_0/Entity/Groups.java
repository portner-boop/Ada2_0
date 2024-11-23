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
//    @OneToMany(mappedBy = "groups", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
//    private List<Resource> resources ;

    public Groups() {}

    public Groups(int  id, String name) {
        this.id = id;
        this.name = name;
    }
//    public void addResource(Resource resource) {
//        if (this.resources == null){
//            this.resources = new ArrayList<>();
//        }
//        resources.add(resource);
//
//    }
//    public void resources(){
//        for (Resource r : resources){
//            System.out.println(r.toString());
//        }
//    }

}
