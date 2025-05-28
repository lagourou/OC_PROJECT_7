package com.nnk.springboot.integration;

import com.nnk.springboot.model.User;
import com.nnk.springboot.repositories.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class UserIT {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userTest() {

        // Create
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("TestPass123");
        user.setFullname("Test User");
        user.setRole("USER");

        // Save
        user = userRepository.save(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals("testUser", user.getUsername());

        // Updates
        user.setRole("ADMIN");
        user = userRepository.save(user);
        Assertions.assertEquals("ADMIN", user.getRole());

        // Find
        List<User> listResult = userRepository.findAll();
        Assertions.assertFalse(listResult.isEmpty());

        // Delete
        Integer id = user.getId();
        userRepository.delete(user);
        Optional<User> userList = userRepository.findById(id);
        Assertions.assertFalse(userList.isPresent());
    }
}
