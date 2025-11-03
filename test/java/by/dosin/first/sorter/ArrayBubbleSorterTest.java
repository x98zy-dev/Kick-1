package by.dosin.first.sorter;

import by.dosin.first.entity.IntArray;
import by.dosin.first.sorter.impl.ArrayBubbleSorter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBubbleSorterTest {

    private ArrayBubbleSorter sorter;

    @BeforeEach
    void setUp() {
        sorter = new ArrayBubbleSorter();
    }

    @Test
    void testSortArraySorted() {
        IntArray array = new IntArray(new int[]{1, 2, 3, 4, 5});
        IntArray result = sorter.sortArray(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result.getArray());
    }

    @Test
    void testSortArrayReverse() {
        IntArray array = new IntArray(new int[]{5, 4, 3, 2, 1});
        IntArray result = sorter.sortArray(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result.getArray());
    }

    @Test
    void testSortArrayRandom() {
        IntArray array = new IntArray(new int[]{3, 1, 4, 2, 5});
        IntArray result = sorter.sortArray(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result.getArray());
    }

    @Test
    void testSortArrayWithDuplicates() {
        IntArray array = new IntArray(new int[]{3, 1, 4, 1, 2});
        IntArray result = sorter.sortArray(array);
        assertArrayEquals(new int[]{1, 1, 2, 3, 4}, result.getArray());
    }

    @Test
    void testSortArraySingleElement() {
        IntArray array = new IntArray(new int[]{5});
        IntArray result = sorter.sortArray(array);
        assertArrayEquals(new int[]{5}, result.getArray());
    }

    @Test
    void testSortArrayEmpty() {
        IntArray array = new IntArray(new int[0]);
        IntArray result = sorter.sortArray(array);
        assertArrayEquals(new int[0], result.getArray());
    }

    @Test
    void testSortArrayNegativeNumbers() {
        IntArray array = new IntArray(new int[]{-3, -1, -4, -2});
        IntArray result = sorter.sortArray(array);
        assertArrayEquals(new int[]{-4, -3, -2, -1}, result.getArray());
    }

    @Test
    void testSortArrayMixedNumbers() {
        IntArray array = new IntArray(new int[]{3, -1, 0, -2, 1});
        IntArray result = sorter.sortArray(array);
        assertArrayEquals(new int[]{-2, -1, 0, 1, 3}, result.getArray());
    }

    @Test
    void testOriginalArrayNotModified() {
        int[] original = {3, 1, 4, 2};
        IntArray array = new IntArray(original);
        sorter.sortArray(array);
        assertArrayEquals(new int[]{3, 1, 4, 2}, original);
    }
}