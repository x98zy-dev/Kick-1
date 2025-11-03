package by.dosin.first.service;

import by.dosin.first.entity.IntArray;
import by.dosin.first.service.impl.ArrayCountSumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayCountSumServiceTest {

    private ArrayCountSumService sumService;
    private IntArray testArray;
    private IntArray emptyArray;
    private IntArray negativeArray;
    private IntArray mixedArray;

    @BeforeEach
    void setUp() {
        sumService = new ArrayCountSumService();
        testArray = new IntArray(new int[]{1, 2, 3, 4, 5});
        emptyArray = new IntArray(new int[0]);
        negativeArray = new IntArray(new int[]{-1, -2, -3});
        mixedArray = new IntArray(new int[]{1, -2, 3, -4, 5});
    }

    @Test
    void testFindSum() {
        int result = sumService.findSum(testArray);
        assertEquals(15, result);
    }

    @Test
    void testFindSumEmptyArray() {
        int result = sumService.findSum(emptyArray);
        assertEquals(0, result);
    }

    @Test
    void testFindSumNegativeNumbers() {
        int result = sumService.findSum(negativeArray);
        assertEquals(-6, result);
    }

    @Test
    void testFindSumMixedNumbers() {
        int result = sumService.findSum(mixedArray);
        assertEquals(3, result);
    }
}