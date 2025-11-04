package by.dosin.first.warehouse;

import by.dosin.first.entity.ArrayData;
import by.dosin.first.entity.IntArray;
import by.dosin.first.observer.ArrayObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Warehouse implements ArrayObserver {
    private static final Logger LOGGER = LogManager.getLogger(Warehouse.class);
    private static Warehouse instance;
    private final Map<String, ArrayData> data;

    private Warehouse() {
        this.data = new HashMap<>();
        LOGGER.debug("Warehouse initialized");
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    @Override
    public void onArrayChanged(IntArray array) {
        LOGGER.debug("Array changed: {}", array.getId());
        ArrayData stats = calculateArrayData(array);
        data.put(array.getId(), stats);
        LOGGER.info("Stats updated for array: {}", array.getId());
    }

    private ArrayData calculateArrayData(IntArray array) {
        int[] elements = array.getArray();
        if (elements.length == 0) {
            return new ArrayData(0, 0, 0, 0);
        }

        int sum = 0;
        int min = elements[0];
        int max = elements[0];

        for (int element : elements) {
            sum += element;
            if (element < min) min = element;
            if (element > max) max = element;
        }

        double average = (double) sum / elements.length;
        return new ArrayData(average, sum, min, max);
    }

    public ArrayData getStats(String id) {
        return data.get(id);
    }

    public void removeStats(String id) {
        LOGGER.debug("Removing stats for array: {}", id);
        data.remove(id);
        LOGGER.info("Stats removed for array: {}", id);
    }

    public void clear() {
        LOGGER.info("Clearing warehouse");
        data.clear();
    }
}