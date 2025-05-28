package com.nnk.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDTO toUserDTO(User user);

    @Mapping(target = "password", ignore = true)
    void updateUserListFromDTO(UserDTO dto, @MappingTarget User entity);

    User toUser(UserDTO userDTO);
}
