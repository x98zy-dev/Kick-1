package by.dosin.first.service;

import by.dosin.first.entity.IntArray;
import by.dosin.first.service.impl.ArrayCountNegativeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayCountNegativeServiceTest {

    private ArrayCountNegativeService countService;
    private IntArray mixedArray;
    private IntArray emptyArray;
    private IntArray allNegativeArray;
    private IntArray noNegativeArray;

    @BeforeEach
    void setUp() {
        countService = new ArrayCountNegativeService();
        mixedArray = new IntArray(new int[]{1, -2, 3, -4, 5, 0, -7});
        emptyArray = new IntArray(new int[0]);
        allNegativeArray = new IntArray(new int[]{-1, -2, -3});
        noNegativeArray = new IntArray(new int[]{1, 2, 3, 0});
    }

    @Test
    void testCountNegativeElementsMixed() {
        int result = countService.countNegativeElements(mixedArray);
        assertEquals(3, result);
    }

    @Test
    void testCountNegativeElementsAllNegative() {
        int result = countService.countNegativeElements(allNegativeArray);
        assertEquals(3, result);
    }

    @Test
    void testCountNegativeElementsNoNegative() {
        int result = countService.countNegativeElements(noNegativeArray);
        assertEquals(0, result);
    }

    @Test
    void testCountNegativeElementsEmptyArray() {
        int result = countService.countNegativeElements(emptyArray);
        assertEquals(0, result);
    }
}