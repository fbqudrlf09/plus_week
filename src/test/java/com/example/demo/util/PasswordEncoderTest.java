package com.example.demo.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class PasswordEncoderTest {

    @Test
    @DisplayName("PasswordEncoder 단위 테스트")
    void passworEncoding() {
        // given
        String val = "1234";

        // when
        String encodingVal = PasswordEncoder.encode(val);

        // then
        assertThat(PasswordEncoder.matches(val, encodingVal)).isTrue();
    }
}