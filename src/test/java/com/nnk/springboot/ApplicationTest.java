package com.nnk.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.TestConfiguration;

@SpringBootTest(classes = ApplicationTest.NoWebConfiguration.class)
public class ApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @TestConfiguration
    @EnableAutoConfiguration
    static class NoWebConfiguration {
    }

    @Test
    void contextLoads() {
        assertNotNull(applicationContext);
    }

    @Test
    void applicationStartsSuccessfully() {
        assertDoesNotThrow(() -> SpringApplication.run(Application.class));
    }

    @Test
    void applicationBeanExists() {
        assertNotNull(applicationContext.getBean(Application.class));
    }

}
