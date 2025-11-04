package by.dosin.first.repository.comparator;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.factory.IntArrayFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComparatorsTest {

    private List<IntArray> arrays;
    private IntArray array1; // id="a", [1, 2, 3], length=3, sum=6
    private IntArray array2; // id="b", [10], length=1, sum=10
    private IntArray array3; // id="c", [5, 5], length=2, sum=10
    private IntArray array4; // id="d", [], length=0, sum=0

    @BeforeEach
    void setUp() throws ArrayAppException {
        arrays = new ArrayList<>();
        array1 = IntArrayFactory.create("a", new int[]{1, 2, 3});
        array2 = IntArrayFactory.create("b", new int[]{10});
        array3 = IntArrayFactory.create("c", new int[]{5, 5});
        array4 = IntArrayFactory.create("d", new int[0]);

        arrays.add(array1);
        arrays.add(array2);
        arrays.add(array3);
        arrays.add(array4);
    }

    @Test
    void testIdComparator() {
        arrays.sort(new IdComparator());

        assertEquals("a", arrays.get(0).getId());
        assertEquals("b", arrays.get(1).getId());
        assertEquals("c", arrays.get(2).getId());
        assertEquals("d", arrays.get(3).getId());
    }

    @Test
    void testIdComparatorReverse() {
        arrays.sort(new IdComparator().reversed());

        assertEquals("d", arrays.get(0).getId());
        assertEquals("c", arrays.get(1).getId());
        assertEquals("b", arrays.get(2).getId());
        assertEquals("a", arrays.get(3).getId());
    }

    @Test
    void testLengthComparator() {
        arrays.sort(new LengthComparator());

        assertEquals(0, arrays.get(0).length()); // array4
        assertEquals(1, arrays.get(1).length()); // array2
        assertEquals(2, arrays.get(2).length()); // array3
        assertEquals(3, arrays.get(3).length()); // array1
    }

    @Test
    void testFirstElementComparator() {
        arrays.sort(new FirstElementComparator());

        // Пустые массивы должны быть первыми
        assertEquals(0, arrays.get(0).length()); // array4 (empty)
        assertEquals(1, arrays.get(1).getArray()[0]); // array1
        assertEquals(5, arrays.get(2).getArray()[0]); // array3
        assertEquals(10, arrays.get(3).getArray()[0]); // array2
    }

    @Test
    void testFirstElementComparatorWithEmptyArrays() throws ArrayAppException {
        List<IntArray> testArrays = new ArrayList<>();
        IntArray empty1 = IntArrayFactory.create("empty1", new int[0]);
        IntArray empty2 = IntArrayFactory.create("empty2", new int[0]);

        testArrays.add(empty1);
        testArrays.add(empty2);
        testArrays.add(array1);

        testArrays.sort(new FirstElementComparator());

        // Оба пустых массива должны быть в начале
        assertTrue(testArrays.get(0).length() == 0);
        assertTrue(testArrays.get(1).length() == 0);
        assertEquals(3, testArrays.get(2).length()); // array1
    }

    @Test
    void testSumComparator() {
        arrays.sort(new SumComparator());

        assertEquals(0, arrays.get(0).getArray().length); // array4 (sum=0)
        assertEquals(3, arrays.get(1).length()); // array1 (sum=6)
        // array2 и array3 имеют одинаковую сумму (10) - порядок не гарантирован
        boolean correctOrder =
                (arrays.get(2).getId().equals("b") && arrays.get(3).getId().equals("c")) ||
                        (arrays.get(2).getId().equals("c") && arrays.get(3).getId().equals("b"));

        assertTrue(correctOrder);
    }

    @Test
    void testSumComparatorWithNegativeNumbers() throws ArrayAppException {
        List<IntArray> testArrays = new ArrayList<>();
        IntArray negative = IntArrayFactory.create("neg", new int[]{-10, -5});
        IntArray positive = IntArrayFactory.create("pos", new int[]{1, 2});
        IntArray zero = IntArrayFactory.create("zero", new int[]{-5, 5});

        testArrays.add(negative);
        testArrays.add(positive);
        testArrays.add(zero);

        testArrays.sort(new SumComparator());

        assertEquals("neg", testArrays.get(0).getId()); // sum=-15
        assertEquals("zero", testArrays.get(1).getId()); // sum=0
        assertEquals("pos", testArrays.get(2).getId()); // sum=3
    }

    @Test
    void testAllComparatorsWithSingleElement() throws ArrayAppException {
        List<IntArray> singleArray = new ArrayList<>();
        IntArray array = IntArrayFactory.create("single", new int[]{42});
        singleArray.add(array);

        // Все компараторы должны корректно работать с одним элементом
        assertDoesNotThrow(() -> singleArray.sort(new IdComparator()));
        assertDoesNotThrow(() -> singleArray.sort(new LengthComparator()));
        assertDoesNotThrow(() -> singleArray.sort(new FirstElementComparator()));
        assertDoesNotThrow(() -> singleArray.sort(new SumComparator()));

        assertEquals(1, singleArray.size());
        assertEquals("single", singleArray.get(0).getId());
    }

    @Test
    void testComparatorConsistency() {
        // Компараторы должны быть консистентными
        IdComparator idComparator = new IdComparator();

        // a < b
        assertTrue(idComparator.compare(array1, array2) < 0);
        // b > a
        assertTrue(idComparator.compare(array2, array1) > 0);
        // a == a
        assertEquals(0, idComparator.compare(array1, array1));
    }
}