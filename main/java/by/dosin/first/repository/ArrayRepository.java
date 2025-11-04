package by.dosin.first.repository;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.queries.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayRepository {
    private static final Logger LOGGER = LogManager.getLogger(ArrayRepository.class);
    private static ArrayRepository instance;
    private final List<IntArray> storage;

    private ArrayRepository() {
        this.storage = new ArrayList<>();
        LOGGER.debug("ArrayRepository initialized");
    }

    public static ArrayRepository getInstance() {
        if (instance == null) {
            instance = new ArrayRepository();
        }
        return instance;
    }

    public void add(IntArray array) throws ArrayAppException {
        LOGGER.debug("Adding array: {}", array);
        if (array == null) {
            LOGGER.error("Attempt to add null array");
            throw new ArrayAppException("Array cannot be null");
        }
        if (contains(array.getId())) {
            LOGGER.warn("Array with id {} already exists", array.getId());
            throw new ArrayAppException("Array with id " + array.getId() + " already exists");
        }
        storage.add(array);
        LOGGER.info("Array added successfully: {}", array.getId());
    }

    public void removeById(String id) throws ArrayAppException {
        LOGGER.debug("Removing array with id: {}", id);
        if (id == null || id.isBlank()) {
            LOGGER.error("Attempt to remove with null or empty id");
            throw new ArrayAppException("ID cannot be null or empty");
        }
        boolean removed = storage.removeIf(array -> array.getId().equals(id));
        if (!removed) {
            LOGGER.warn("Array with id {} not found for removal", id);
            throw new ArrayAppException("Array with id " + id + " not found");
        }
        LOGGER.info("Array removed successfully: {}", id);
    }

    public List<IntArray> getAll() {
        LOGGER.debug("Retrieving all arrays, count: {}", storage.size());
        return new ArrayList<>(storage);
    }

    public List<IntArray> query(Specification specification) {
        return storage.stream()
                .filter(specification.toPredicate())
                .collect(Collectors.toList());
    }

    public boolean contains(String id) throws ArrayAppException {
        if (id == null || id.isBlank()) {
            throw new ArrayAppException("ID cannot be null or empty");
        }
        return storage.stream().anyMatch(array -> array.getId().equals(id));
    }

    public void clear() {
        LOGGER.info("Clearing repository, current size: {}", storage.size());
        storage.clear();
        LOGGER.debug("Repository cleared");
    }

    public int size() {
        return storage.size();
    }
}