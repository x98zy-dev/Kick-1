package by.dosin.first.service;

import by.dosin.first.entity.IntArray;
import by.dosin.first.service.impl.ArrayCountPositiveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayCountPositiveServiceTest {

    private ArrayCountPositiveService countService;
    private IntArray mixedArray;
    private IntArray emptyArray;
    private IntArray allPositiveArray;
    private IntArray noPositiveArray;

    @BeforeEach
    void setUp() {
        countService = new ArrayCountPositiveService();
        mixedArray = new IntArray(new int[]{1, -2, 3, -4, 5, 0, -7});
        emptyArray = new IntArray(new int[0]);
        allPositiveArray = new IntArray(new int[]{1, 2, 3});
        noPositiveArray = new IntArray(new int[]{-1, -2, -3, 0});
    }

    @Test
    void testCountPositiveElementsMixed() {
        int result = countService.countPositiveElements(mixedArray);
        assertEquals(3, result);
    }

    @Test
    void testCountPositiveElementsAllPositive() {
        int result = countService.countPositiveElements(allPositiveArray);
        assertEquals(3, result);
    }

    @Test
    void testCountPositiveElementsNoPositive() {
        int result = countService.countPositiveElements(noPositiveArray);
        assertEquals(0, result);
    }

    @Test
    void testCountPositiveElementsEmptyArray() {
        int result = countService.countPositiveElements(emptyArray);
        assertEquals(0, result);
    }
}