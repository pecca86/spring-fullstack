package com.example.fullstack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FullstackApplicationTests {

    @Test
    void contextLoads() {
        // Make a failing test to check dev pipeline
        Assertions.fail("Test failed");
    }

}
