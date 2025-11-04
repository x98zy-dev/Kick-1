package by.dosin.first.warehouse;

import by.dosin.first.entity.ArrayData;
import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.factory.IntArrayFactory;
import by.dosin.first.observer.ArrayObserver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    private Warehouse warehouse;
    private IntArray testArray;
    private IntArray emptyArray;

    @BeforeEach
    void setUp() throws ArrayAppException {
        warehouse = Warehouse.getInstance();
        testArray = IntArrayFactory.create("arr1", new int[]{1, 2, 3, 4, 5});
        emptyArray = IntArrayFactory.create("empty", new int[0]);
    }

    @AfterEach
    void tearDown() {
        warehouse.clear();
    }

    @Test
    void testRegisterArray() {
        warehouse.register(testArray);

        ArrayData stats = warehouse.getStats("arr1");

        assertNotNull(stats);
        assertEquals(15, stats.getSum());
        assertEquals(3.0, stats.getAverage(), 0.001);
        assertEquals(1, stats.getMin());
        assertEquals(5, stats.getMax());
    }

    @Test
    void testRegisterEmptyArray() {
        warehouse.register(emptyArray);

        ArrayData stats = warehouse.getStats("empty");

        assertNotNull(stats);
        assertEquals(0, stats.getSum());
        assertEquals(0, stats.getAverage(), 0.001);
        assertEquals(0, stats.getMin());
        assertEquals(0, stats.getMax());
    }

    @Test
    void testUpdateObserver() throws ArrayAppException {
        warehouse.register(testArray);

        // Изменяем массив - должен автоматически обновиться Warehouse
        testArray.setArray(new int[]{10, 20, 30});

        ArrayData stats = warehouse.getStats("arr1");

        assertEquals(60, stats.getSum());
        assertEquals(20.0, stats.getAverage(), 0.001);
        assertEquals(10, stats.getMin());
        assertEquals(30, stats.getMax());
    }

    @Test
    void testGetStatsNonExistentId() {
        ArrayData stats = warehouse.getStats("nonexistent");

        assertNull(stats);
    }

    @Test
    void testRemoveStats() {
        warehouse.register(testArray);

        warehouse.removeStats("arr1");

        ArrayData stats = warehouse.getStats("arr1");
        assertNull(stats);
    }

    @Test
    void testRemoveNonExistentStats() {
        // Не должно бросать исключение
        assertDoesNotThrow(() -> {
            warehouse.removeStats("nonexistent");
        });
    }

    @Test
    void testClearWarehouse() {
        warehouse.register(testArray);
        warehouse.register(emptyArray);

        warehouse.clear();

        assertNull(warehouse.getStats("arr1"));
        assertNull(warehouse.getStats("empty"));
    }

    @Test
    void testCalculateArrayDataWithNegativeNumbers() throws ArrayAppException {
        IntArray negativeArray = IntArrayFactory.create("negative", new int[]{-5, -2, -8, -1});
        warehouse.register(negativeArray);

        ArrayData stats = warehouse.getStats("negative");

        assertEquals(-16, stats.getSum());
        assertEquals(-4.0, stats.getAverage(), 0.001);
        assertEquals(-8, stats.getMin());
        assertEquals(-1, stats.getMax());
    }

    @Test
    void testCalculateArrayDataSingleElement() throws ArrayAppException {
        IntArray singleArray = IntArrayFactory.create("single", new int[]{42});
        warehouse.register(singleArray);

        ArrayData stats = warehouse.getStats("single");

        assertEquals(42, stats.getSum());
        assertEquals(42.0, stats.getAverage(), 0.001);
        assertEquals(42, stats.getMin());
        assertEquals(42, stats.getMax());
    }

    @Test
    void testMultipleObservers() throws ArrayAppException {
        ArrayObserver customObserver = new ArrayObserver() {
            @Override
            public void update(IntArray array) {
            }
        };

        testArray.addObserver(warehouse);
        testArray.addObserver(customObserver);

        assertDoesNotThrow(() -> {
            testArray.setArray(new int[]{100, 200});
        });

        ArrayData stats = warehouse.getStats("arr1");
        assertEquals(300, stats.getSum());

        testArray.removeObserver(customObserver);
    }
}