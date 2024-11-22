package org.example.springboot.ada2_0.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GroupDto {
    private int id;
    private String name ;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> resources;
}
