package org.example.springboot.ada2_0.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="resources")
public class Resources {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="group_id")
    private int group_id;
    @Column(name="name")
    private String name;

    public Resources() {}

    public Resources(long id, int group_id, String name) {
        this.id = id;
        this.group_id = group_id;
        this.name = name;
    }
}
