package by.dosin.first.service;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.service.impl.ArrayAverageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayAverageServiceTest {

    private ArrayAverageService averageService;
    private IntArray testArray;
    private IntArray emptyArray;

    @BeforeEach
    void setUp() {
        averageService = new ArrayAverageService();
        testArray = new IntArray(new int[]{1, 2, 3, 4, 5});
        emptyArray = new IntArray(new int[0]);
    }

    @Test
    void testCalculateSum() {
        int expectedSum = 15;
        int result = averageService.calculateSum(testArray);
        assertEquals(expectedSum, result);
    }

    @Test
    void testCalculateSumWithNegativeNumbers() {
        IntArray arrayWithNegatives = new IntArray(new int[]{1, -2, 3, -4, 5});
        int expectedSum = 3; // 1 + (-2) + 3 + (-4) + 5 = 3
        int result = averageService.calculateSum(arrayWithNegatives);
        assertEquals(expectedSum, result);
    }

    @Test
    void testCalculateSumEmptyArray() {
        int expectedSum = 0;
        int result = averageService.calculateSum(emptyArray);
        assertEquals(expectedSum, result);
    }

    @Test
    void testCalculateAverage() throws ArrayAppException {
        double expectedAverage = 3.0;
        double result = averageService.calculateAverage(testArray);
        assertEquals(expectedAverage, result, 0.001);
    }

    @Test
    void testCalculateAverageWithDecimalResult() throws ArrayAppException {
        IntArray array = new IntArray(new int[]{1, 2, 3});
        double expectedAverage = 2.0;
        double result = averageService.calculateAverage(array);
        assertEquals(expectedAverage, result, 0.001);
    }

    @Test
    void testCalculateAverageEmptyArrayThrowsException() {
        assertThrows(ArrayAppException.class, () -> {
            averageService.calculateAverage(emptyArray);
        });
    }

    @Test
    void testCalculateAverageExceptionMessage() {
        String expectedMessage = "Нельзя вычислить среднее значение пустого массива";
        ArrayAppException exception = assertThrows(ArrayAppException.class, () -> {
            averageService.calculateAverage(emptyArray);
        });

        assertTrue(exception.getMessage().contains(expectedMessage));
    }
}