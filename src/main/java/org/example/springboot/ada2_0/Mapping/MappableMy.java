package org.example.springboot.ada2_0.Mapping;

import java.util.List;

public interface MappableMy<E,D>{
    D toDto(E entity);
    List<D> toDto(List<E> entities);
    E toEntity(D dto);
    List <E> toEntity(List<D> dtos);
}
