package by.dosin.first.queries;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.factory.IntArrayFactory;
import by.dosin.first.queries.impl.AverageLessSpecification;
import by.dosin.first.queries.impl.IdSpecification;
import by.dosin.first.queries.impl.SumGreaterSpecification;
import by.dosin.first.repository.ArrayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecificationsTest {

    private ArrayRepository repository;
    private IntArray array1;
    private IntArray array2;
    private IntArray array3;

    @BeforeEach
    void setUp() throws ArrayAppException {
        repository = ArrayRepository.getInstance();
        repository.clear();

        array1 = IntArrayFactory.create("arr1", new int[]{1, 2, 3});       // sum=6, avg=2.0
        array2 = IntArrayFactory.create("arr2", new int[]{10, 20, 30});    // sum=60, avg=20.0
        array3 = IntArrayFactory.create("arr3", new int[]{-5, 0, 5});      // sum=0, avg=0.0

        repository.add(array1);
        repository.add(array2);
        repository.add(array3);
    }

    @Test
    void testIdSpecificationFound() {
        Specification spec = new IdSpecification("arr1");
        List<IntArray> result = repository.query(spec);

        assertEquals(1, result.size());
        assertEquals("arr1", result.get(0).getId());
    }

    @Test
    void testIdSpecificationNotFound() {
        Specification spec = new IdSpecification("nonexistent");
        List<IntArray> result = repository.query(spec);

        assertTrue(result.isEmpty());
    }

    @Test
    void testSumGreaterSpecification() {
        Specification spec = new SumGreaterSpecification(10);
        List<IntArray> result = repository.query(spec);

        assertEquals(1, result.size());
        assertEquals("arr2", result.get(0).getId());
    }

    @Test
    void testSumGreaterSpecificationNoMatches() {
        Specification spec = new SumGreaterSpecification(100);
        List<IntArray> result = repository.query(spec);

        assertTrue(result.isEmpty());
    }

    @Test
    void testSumGreaterSpecificationZeroThreshold() {
        Specification spec = new SumGreaterSpecification(0);
        List<IntArray> result = repository.query(spec);

        assertEquals(2, result.size()); // arr1 (sum=6), arr2 (sum=60)
    }

    @Test
    void testAverageLessSpecification() {
        Specification spec = new AverageLessSpecification(10.0);
        List<IntArray> result = repository.query(spec);

        assertEquals(2, result.size()); // arr1 (avg=2.0), arr3 (avg=0.0)
    }

    @Test
    void testAverageLessSpecificationNoMatches() {
        Specification spec = new AverageLessSpecification(-1.0);
        List<IntArray> result = repository.query(spec);

        assertTrue(result.isEmpty());
    }

    @Test
    void testAverageLessSpecificationAllMatches() {
        Specification spec = new AverageLessSpecification(50.0);
        List<IntArray> result = repository.query(spec);

        assertEquals(3, result.size()); // Все массивы
    }

    @Test
    void testCombinedSpecifications() throws ArrayAppException {
        // Добавляем ещё один массив для теста комбинаций
        IntArray array4 = IntArrayFactory.create("arr4", new int[]{5, 5, 5}); // sum=15, avg=5.0
        repository.add(array4);

        // Ищем массивы с суммой > 5 и средним < 10
        Specification sumSpec = new SumGreaterSpecification(5);
        Specification avgSpec = new AverageLessSpecification(10.0);

        List<IntArray> result1 = repository.query(sumSpec);
        List<IntArray> result2 = repository.query(avgSpec);

        // arr1 (sum=6, avg=2.0), arr4 (sum=15, avg=5.0) подходят под оба условия
        assertEquals(3, result1.size()); // arr1, arr2, arr4
        assertEquals(3, result2.size()); // arr1, arr3, arr4
    }

    @Test
    void testEmptyRepository() {
        repository.clear();

        Specification spec = new IdSpecification("arr1");
        List<IntArray> result = repository.query(spec);

        assertTrue(result.isEmpty());
    }

    @Test
    void testSpecificationWithEmptyArray() throws ArrayAppException {
        IntArray emptyArray = IntArrayFactory.create("empty", new int[0]);
        repository.add(emptyArray);

        // Пустой массив: sum=0, avg=0
        Specification sumSpec = new SumGreaterSpecification(-1);
        Specification avgSpec = new AverageLessSpecification(1.0);

        List<IntArray> sumResult = repository.query(sumSpec);
        List<IntArray> avgResult = repository.query(avgSpec);

        // Пустой массив должен быть найден по sum > -1 (0 > -1 = true)
        assertTrue(sumResult.stream().anyMatch(arr -> arr.getId().equals("empty")));
        // Пустой массив должен быть найден по avg < 1.0 (0.0 < 1.0 = true)
        assertTrue(avgResult.stream().anyMatch(arr -> arr.getId().equals("empty")));
    }
}