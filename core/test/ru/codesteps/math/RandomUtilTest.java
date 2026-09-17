package ru.codesteps.math;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for RandomUtil class.
 */
class RandomUtilTest {

    @RepeatedTest(100)
    void nextFloat_shouldReturnValueWithinRange() {
        float min = 10.0f;
        float max = 20.0f;
        
        float result = RandomUtil.nextFloat(min, max);
        
        assertTrue(result >= min, "Result should be >= min");
        assertTrue(result <= max, "Result should be <= max");
    }

    @RepeatedTest(50)
    void nextFloat_withNegativeRange_shouldReturnValueWithinRange() {
        float min = -100.0f;
        float max = -50.0f;
        
        float result = RandomUtil.nextFloat(min, max);
        
        assertTrue(result >= min, "Result should be >= min");
        assertTrue(result <= max, "Result should be <= max");
    }

    @RepeatedTest(50)
    void nextFloat_withMixedRange_shouldReturnValueWithinRange() {
        float min = -10.0f;
        float max = 10.0f;
        
        float result = RandomUtil.nextFloat(min, max);
        
        assertTrue(result >= min, "Result should be >= min");
        assertTrue(result <= max, "Result should be <= max");
    }

    @Test
    void nextFloat_withSameMinMax_shouldReturnThatValue() {
        float value = 5.0f;
        
        float result = RandomUtil.nextFloat(value, value);
        
        assertEquals(value, result, 0.0001f);
    }

    @Test
    void nextFloat_withZeroRange_shouldReturnZero() {
        float result = RandomUtil.nextFloat(0.0f, 0.0f);
        
        assertEquals(0.0f, result, 0.0001f);
    }
}
