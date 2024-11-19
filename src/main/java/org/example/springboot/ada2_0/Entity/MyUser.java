package org.example.springboot.ada2_0.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name = "group_id")
    private int group_id;

    public MyUser() {}

    public MyUser(long id, String password, int group_id) {
        this.id = id;
        this.password = password;
        this.group_id = group_id;
    }

}
