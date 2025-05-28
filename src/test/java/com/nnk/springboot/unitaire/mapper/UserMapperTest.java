package com.nnk.springboot.unitaire.mapper;

import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.mapper.UserMapper;
import com.nnk.springboot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @Test
    public void testToUserDTO() {
        User user = new User();
        user.setId(1);
        user.setUsername("TestUser");
        user.setFullname("Test User");
        user.setRole("USER");
        user.setPassword("SecretPassword");

        UserDTO userDTO = userMapper.toUserDTO(user);

        assertNotNull(userDTO);
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getUsername(), userDTO.getUsername());
        assertEquals(user.getFullname(), userDTO.getFullname());
        assertEquals(user.getRole(), userDTO.getRole());
    }

    @Test
    public void testToUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setUsername("TestUser");
        userDTO.setFullname("Test User");
        userDTO.setRole("ADMIN");

        User user = userMapper.toUser(userDTO);

        assertNotNull(user);
        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getFullname(), user.getFullname());
        assertEquals(userDTO.getRole(), user.getRole());
    }

    @Test
    public void testUpdateUserListFromDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("UpdatedUser");
        userDTO.setFullname("Updated Name");
        userDTO.setRole("ADMIN");

        User user = new User();
        user.setId(1);
        user.setUsername("OldUser");
        user.setFullname("Old Name");
        user.setRole("USER");
        user.setPassword("OldPassword");

        userMapper.updateUserListFromDTO(userDTO, user);

        assertEquals("UpdatedUser", user.getUsername());
        assertEquals("Updated Name", user.getFullname());
        assertEquals("ADMIN", user.getRole());
        assertEquals("OldPassword", user.getPassword());
    }
}
