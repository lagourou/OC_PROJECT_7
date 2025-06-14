package com.nnk.springboot.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by Khang Nguyen.
 * Email: khang.nguyen@banvien.com
 * Date:
 * 09/03/2019
 * Time: 11:26 AM
 */
@SpringBootTest
@ActiveProfiles("test")
public class PasswordEncodeIT {
    @Test
    public void testPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pw = encoder.encode("123456");
        System.out.println("[ " + pw + " ]");
        Assertions.assertNotNull(pw);
    }
}
