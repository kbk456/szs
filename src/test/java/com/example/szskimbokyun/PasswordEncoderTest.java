package com.example.szskimbokyun;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class PasswordEncoderTest {

    @Test
    void testPasswordEncodeAndMatch(){

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //given
        String plainPassword = "921108-1582816";

        //when
        String encoded = passwordEncoder.encode(plainPassword);
        System.out.println("encoded:"+encoded);

        encoded = "$2a$10$ETlTXpxhW6yO4v.P3yjyQe6Kgc0QHkyeAs8RFL6PpQPbrshRZ0XIS";
        // Then
        boolean isMatch = passwordEncoder.matches(plainPassword, encoded);
        System.out.println("Is match: " + isMatch); // This should print true

    }
}
