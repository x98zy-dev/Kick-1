
package by.dosin.first.reader;

import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.reader.impl.ArrayFileReaderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayArrayFileReaderImplTest {

    private ArrayFileReaderImpl fileReader;

    @BeforeEach
    void setUp() {
        fileReader = new ArrayFileReaderImpl();
    }

    @Test
    void testReadLinesFromFile(@TempDir Path tempDir) throws IOException, ArrayAppException {
        Path testFile = tempDir.resolve("test.txt");
        Files.write(testFile, List.of("1,2,3", "4,5,6", "7,8,9"));

        ArrayFileReaderImpl reader = new ArrayFileReaderImpl() {
            @Override
            public List<String> readLinesFromFile(String filename) {
                try {
                    return Files.readAllLines(testFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        List<String> result = reader.readLinesFromFile("test.txt");
        assertEquals(3, result.size());
        assertEquals("1,2,3", result.get(0));
    }

    @Test
    void testReadLinesFromFileWithValidData(@TempDir Path tempDir) throws IOException, ArrayAppException {
        Path testFile = tempDir.resolve("arrays.txt");
        Files.write(testFile, List.of("1,2,3", "4,5,6"));

        ArrayFileReaderImpl reader = new ArrayFileReaderImpl() {
            @Override
            public List<String> readLinesFromFile(String filename) {
                try {
                    return Files.readAllLines(testFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        List<String> result = reader.readLinesFromFile("arrays.txt");
        assertEquals(2, result.size());
    }

    @Test
    void testReadLinesFromNonExistentFile() {
        ArrayAppException exception = assertThrows(ArrayAppException.class, () -> {
            fileReader.readLinesFromFile("nonexistent.txt");
        });
        assertTrue(exception.getMessage().contains("Файл не найден"));
    }

    @Test
    void testReadLinesFromEmptyFile(@TempDir Path tempDir) throws IOException, ArrayAppException {
        Path testFile = tempDir.resolve("empty.txt");
        Files.createFile(testFile);

        ArrayFileReaderImpl reader = new ArrayFileReaderImpl() {
            @Override
            public List<String> readLinesFromFile(String filename) {
                try {
                    return Files.readAllLines(testFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        List<String> result = reader.readLinesFromFile("empty.txt");
        assertTrue(result.isEmpty());
    }
}