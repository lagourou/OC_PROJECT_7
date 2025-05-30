package com.nnk.springboot.mapper;

import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T20:38:23+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250526-2018, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setFullname( user.getFullname() );
        userDTO.setId( user.getId() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setRole( user.getRole() );
        userDTO.setUsername( user.getUsername() );

        return userDTO;
    }

    @Override
    public void updateUserListFromDTO(UserDTO dto, User entity) {
        if ( dto == null ) {
            return;
        }

        entity.setFullname( dto.getFullname() );
        entity.setId( dto.getId() );
        entity.setRole( dto.getRole() );
        entity.setUsername( dto.getUsername() );
    }

    @Override
    public User toUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setFullname( userDTO.getFullname() );
        user.setId( userDTO.getId() );
        user.setPassword( userDTO.getPassword() );
        user.setRole( userDTO.getRole() );
        user.setUsername( userDTO.getUsername() );

        return user;
    }
}
