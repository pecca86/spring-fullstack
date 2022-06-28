package com.example.fullstack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class FullstackApplicationTests {

    @Test
    void contextLoads() {

        System.out.println("Some text to make changes...");
        System.out.println("Added adding-text");
        // Make a failing test to check dev pipeline
//        Assertions.fail("FAILING TEST ON PURPOSE");
    }
}
