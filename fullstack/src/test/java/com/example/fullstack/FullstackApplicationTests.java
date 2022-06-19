package com.example.fullstack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class FullstackApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("pitten ska ha sitt!");
        System.out.println("Some text to make changes...");
        // Make a failing test to check dev pipeline
        Assertions.fail("FAILING TEST ON PURPOSE");
    }
}
