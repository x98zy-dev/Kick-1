package by.dosin.first.service;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.service.impl.ArrayFindMaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayFindMaxTest {

    private ArrayFindMaxService maxService;
    private IntArray testArray;
    private IntArray singleElementArray;
    private IntArray emptyArray;
    private IntArray negativeArray;

    @BeforeEach
    void setUp() {
        maxService = new ArrayFindMaxService();
        testArray = new IntArray(new int[]{1, 5, 3, 9, 2});
        singleElementArray = new IntArray(new int[]{7});
        emptyArray = new IntArray(new int[0]);
        negativeArray = new IntArray(new int[]{-1, -5, -3});
    }

    @Test
    void testFindMax() throws ArrayAppException {
        int result = maxService.findMax(testArray);
        assertEquals(9, result);
    }

    @Test
    void testFindMaxSingleElement() throws ArrayAppException {
        int result = maxService.findMax(singleElementArray);
        assertEquals(7, result);
    }

    @Test
    void testFindMaxNegativeNumbers() throws ArrayAppException {
        int result = maxService.findMax(negativeArray);
        assertEquals(-1, result);
    }

    @Test
    void testFindMaxEmptyArray() {
        assertThrows(ArrayAppException.class, () -> {
            maxService.findMax(emptyArray);
        });
    }
}