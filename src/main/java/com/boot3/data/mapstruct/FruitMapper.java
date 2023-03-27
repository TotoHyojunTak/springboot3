package com.boot3.data.mapstruct;

import com.boot3.data.dto.response.FruitDTO;
import com.boot3.data.entity.FruitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface FruitMapper {
    FruitMapper INSTANCE = Mappers.getMapper(FruitMapper.class);

    @Mapping(target="seq", expression="java(entity.getSeq())")
    @Mapping(target="name", expression="java(entity.getName())")
    @Mapping(target="season", expression="java(entity.getSeason())")
    @Mapping(target="price", expression="java(entity.getPrice())")
    @Mapping(target="region", expression="java(entity.getRegion())")
    @Mapping(target="createdDate", expression="java(entity.getCreatedDate())")
    FruitDTO toDto(FruitEntity entity);

    List<FruitDTO> toDtoList(List<FruitEntity> entityList);



}