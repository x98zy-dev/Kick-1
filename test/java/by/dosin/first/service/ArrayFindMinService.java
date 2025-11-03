package by.dosin.first.service;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.service.impl.ArrayFindMinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayFindMinServiceTest {

    private ArrayFindMinService minService;
    private IntArray testArray;
    private IntArray singleElementArray;
    private IntArray emptyArray;
    private IntArray negativeArray;

    @BeforeEach
    void setUp() {
        minService = new ArrayFindMinService();
        testArray = new IntArray(new int[]{5, 1, 9, 3, 2});
        singleElementArray = new IntArray(new int[]{7});
        emptyArray = new IntArray(new int[0]);
        negativeArray = new IntArray(new int[]{-1, -5, -3});
    }

    @Test
    void testFindMin() throws ArrayAppException {
        int result = minService.findMin(testArray);
        assertEquals(1, result);
    }

    @Test
    void testFindMinSingleElement() throws ArrayAppException {
        int result = minService.findMin(singleElementArray);
        assertEquals(7, result);
    }

    @Test
    void testFindMinNegativeNumbers() throws ArrayAppException {
        int result = minService.findMin(negativeArray);
        assertEquals(-5, result);
    }

    @Test
    void testFindMinEmptyArray() {
        assertThrows(ArrayAppException.class, () -> {
            minService.findMin(emptyArray);
        });
    }
}