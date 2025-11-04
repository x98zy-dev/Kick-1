package by.dosin.first.reader.impl;

import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.reader.ArrayFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ArrayFileReaderImpl implements ArrayFileReader {

    private static final Logger LOGGER = LogManager.getLogger(ArrayFileReaderImpl.class);
    private static final String DATA_DIRECTORY = "data";

    public List<String> readLinesFromFile(String filename) throws ArrayAppException {
        Path filePath = Paths.get(DATA_DIRECTORY, filename);

        LOGGER.debug("Reading file: {}", filePath);

        if (!Files.exists(filePath)) {
            LOGGER.error("File not found: {}", filePath);
            throw new ArrayAppException("File not found: " + filePath);
        }

        List<String> lines;

        try {
            lines = Files.readAllLines(filePath);
            LOGGER.info("Successfully read {} lines from file: {}", lines.size(), filename);
        } catch (IOException exception) {
            LOGGER.error("Error reading file: {}", filePath, exception);
            throw new ArrayAppException("Error reading file: " + filePath, exception);
        }

        return lines;
    }
}