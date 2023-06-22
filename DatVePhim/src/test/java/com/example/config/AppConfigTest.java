package com.example.config;
import com.example.services.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class AppConfigTest {

    @Test
    public void testPasswordEncoder() {
        AppConfig appConfig = new AppConfig(new CustomUserDetailsService());
        BCryptPasswordEncoder encoder = appConfig.passwordEncoder();
        String password = "mypassword";
        String encodedPassword = encoder.encode(password);
        assertTrue(encoder.matches(password, encodedPassword));
    }

    // Passed
}