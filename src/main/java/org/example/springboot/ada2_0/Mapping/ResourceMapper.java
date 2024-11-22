package org.example.springboot.ada2_0.Mapping;

import org.example.springboot.ada2_0.Dto.ResourceDto;
import org.example.springboot.ada2_0.Entity.Resource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResourceMapper extends MappableMy<Resource, ResourceDto> {
}
