package by.dosin.first.repository;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.factory.IntArrayFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRepositoryTest {

    private ArrayRepository repository;
    private IntArray testArray1;
    private IntArray testArray2;

    @BeforeEach
    void setUp() throws ArrayAppException {
        repository = ArrayRepository.getInstance();
        testArray1 = IntArrayFactory.create("arr1", new int[]{1, 2, 3});
        testArray2 = IntArrayFactory.create("arr2", new int[]{4, 5, 6});
    }

    @AfterEach
    void tearDown() {
        repository.clear();
    }

    @Test
    void testAddArray() throws ArrayAppException {
        repository.add(testArray1);

        List<IntArray> result = repository.getAll();

        assertEquals(1, result.size());
        assertEquals("arr1", result.get(0).getId());
    }

    @Test
    void testAddDuplicateIdThrowsException() throws ArrayAppException {
        repository.add(testArray1);

        assertThrows(ArrayAppException.class, () -> {
            repository.add(testArray1);
        });
    }

    @Test
    void testAddNullArrayThrowsException() {
        assertThrows(ArrayAppException.class, () -> {
            repository.add(null);
        });
    }

    @Test
    void testRemoveById() throws ArrayAppException {
        repository.add(testArray1);
        repository.add(testArray2);

        repository.removeById("arr1");

        List<IntArray> result = repository.getAll();
        assertEquals(1, result.size());
        assertEquals("arr2", result.get(0).getId());
    }

    @Test
    void testRemoveNonExistentIdThrowsException() {
        assertThrows(ArrayAppException.class, () -> {
            repository.removeById("nonexistent");
        });
    }

    @Test
    void testRemoveWithNullIdThrowsException() {
        assertThrows(ArrayAppException.class, () -> {
            repository.removeById(null);
        });
    }

    @Test
    void testGetAllEmptyRepository() {
        List<IntArray> result = repository.getAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllMultipleArrays() throws ArrayAppException {
        repository.add(testArray1);
        repository.add(testArray2);

        List<IntArray> result = repository.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void testContainsExistingId() throws ArrayAppException {
        repository.add(testArray1);

        boolean result = repository.contains("arr1");

        assertTrue(result);
    }

    @Test
    void testContainsNonExistentId() throws ArrayAppException {
        repository.add(testArray1);

        boolean result = repository.contains("arr999");

        assertFalse(result);
    }

    @Test
    void testContainsWithNullIdThrowsException() {
        assertThrows(ArrayAppException.class, () -> {
            repository.contains(null);
        });
    }

    @Test
    void testClearRepository() throws ArrayAppException {
        repository.add(testArray1);
        repository.add(testArray2);

        repository.clear();

        List<IntArray> result = repository.getAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void testSize() throws ArrayAppException {
        assertEquals(0, repository.size());

        repository.add(testArray1);
        assertEquals(1, repository.size());

        repository.add(testArray2);
        assertEquals(2, repository.size());

        repository.removeById("arr1");
        assertEquals(1, repository.size());
    }
}