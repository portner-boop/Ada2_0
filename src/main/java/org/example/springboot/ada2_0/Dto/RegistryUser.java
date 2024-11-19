package org.example.springboot.ada2_0.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistryUser {

    private int id;
    private String username;
    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 8, max = 100, message = "Пароль должен быть не менее 8 символов")
    private String password;
    private String pg_password;

    public RegistryUser() {
    }

    public RegistryUser(int id, String username, String password, String pg_password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.pg_password = pg_password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPg_password() {
        return pg_password;
    }

    public void setPg_password(String pg_password) {
        this.pg_password = pg_password;
    }
}
