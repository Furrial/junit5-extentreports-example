package com.example;

import com.example.reporter.TestReporter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(TestReporter.class)
public class DivisionTest {
    @Test
    void divide1And1() {
        assertTrue(1 / 1 == 1);
    }

    @Test
    void intentionallyFailing() {
        assertEquals(1, 0);
    }
}
