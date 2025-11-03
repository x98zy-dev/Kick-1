package by.dosin.first.service;

import by.dosin.first.entity.IntArray;
import by.dosin.first.service.impl.ArrayElementsReplaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayElementsReplaceServiceTest {

    private ArrayElementsReplaceService replaceService;
    private IntArray testArray;

    @BeforeEach
    void setUp() {
        replaceService = new ArrayElementsReplaceService();
        testArray = new IntArray(new int[]{1, 2, 3, 2, 5});
    }

    @Test
    void testReplaceElementsSingleOccurrence() {
        IntArray result = replaceService.replaceElements(testArray, 3, 10);
        assertArrayEquals(new int[]{1, 2, 10, 2, 5}, result.getArray());
    }

    @Test
    void testReplaceElementsMultipleOccurrences() {
        IntArray result = replaceService.replaceElements(testArray, 2, 8);
        assertArrayEquals(new int[]{1, 8, 3, 8, 5}, result.getArray());
    }

    @Test
    void testReplaceElementsNoOccurrences() {
        IntArray result = replaceService.replaceElements(testArray, 7, 10);
        assertArrayEquals(new int[]{1, 2, 3, 2, 5}, result.getArray());
    }

    @Test
    void testReplaceElementsEmptyArray() {
        IntArray emptyArray = new IntArray(new int[0]);
        IntArray result = replaceService.replaceElements(emptyArray, 1, 2);
        assertArrayEquals(new int[0], result.getArray());
    }
}