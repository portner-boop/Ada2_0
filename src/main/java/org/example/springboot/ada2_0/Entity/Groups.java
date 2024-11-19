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
    @OneToMany(mappedBy = "group_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Resources> resources = new ArrayList<>();

    public Groups() {}

    public Groups(int  id, String name) {
        this.id = id;
        this.name = name;
    }
    public void addResorce(){
        resources.add(new Resources());
    }

}
