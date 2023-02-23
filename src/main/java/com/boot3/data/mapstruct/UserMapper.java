package com.boot3.data.mapstruct;

import com.boot3.data.dto.request.UserSaveReqDTO;
import com.boot3.data.dto.response.UserDTO;
import com.boot3.data.dto.response.UserRecordDTO;
import com.boot3.data.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(target="pwd", expression="java(entity.getEncryptedPwd())")
    UserRecordDTO toDto(UserEntity entity);



    @Mapping(target="encryptedPwd", expression="java(dto.getPwd())")
    UserEntity toEntity(UserSaveReqDTO dto);


    List<UserRecordDTO> toDtoList(List<UserEntity> entityList);

    List<UserEntity> toEntityList(List<UserSaveReqDTO> dtoList);


}