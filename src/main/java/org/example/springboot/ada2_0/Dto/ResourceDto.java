package org.example.springboot.ada2_0.Dto;

import lombok.Data;

@Data
public class ResourceDto {
    String name;
    String url;

    public ResourceDto(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
