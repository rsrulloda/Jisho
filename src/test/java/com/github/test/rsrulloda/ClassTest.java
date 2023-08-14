package com.github.test.rsrulloda;

import com.github.rsrulloda.RandomIDGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClassTest {

    @Test
    void testSomeMethod() {
        RandomIDGenerator randomIDGenerator = new RandomIDGenerator(100);
        int result = randomIDGenerator.getRandomID();
        assertEquals(5, result);
    }
}