package com.shopme.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordEncoderTest {

    @Test
    void encodingTest() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String plainPassword = "test1234";
        String encodedPassword = encoder.encode(plainPassword);

        assertThat(encoder.matches(plainPassword, encodedPassword)).isTrue();
    }
}
