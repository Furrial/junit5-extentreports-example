package com.example;


import com.example.reporter.TestReporter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(TestReporter.class)
public class MultiplicationTest {
    @Test
    void multipy1And1() {
        assertTrue(1 * 1 == 1);
    }

    @Test
    void multipy2And1() {
        assertTrue(2 * 1 == 2);
    }
}
