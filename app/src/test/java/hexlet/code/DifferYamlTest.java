package hexlet.code;

import hexlet.code.formatters.Formatter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferYamlTest {
    private static String absoluteFilePath1;
    private static String absoluteFilePath2;
    private static String absoluteFilePath3;
    private static String absoluteFilePath4;
    private static String absoluteFilePath5;
    private static String absoluteFilePath6;
    private static String absoluteEmptyFilePath;
    private static String absoluteOutputFilePath1;
    private static String absoluteOutputFilePath2;
    private static String absoluteOutputFilePath3;
    private static String absoluteOutputFilePath4;
    private static String absoluteOutputFilePath5;
    private static String absoluteOutputFilePath6;

    @BeforeAll
    static void setUp() {
        String resourcesPath = new File("src/test/resources").getAbsolutePath();

        String filePath1 = "/fixtures/file1.yaml";
        String filePath2 = "/fixtures/file2.yaml";
        String filePath3 = "/fixtures/file3.yaml";
        String filePath4 = "/fixtures/file4.yaml";
        String filePath5 = "/fixtures/file5.yaml";
        String filePath6 = "/fixtures/file6.yaml";
        String emptyFilePath1 = "/fixtures/empty.yaml";
        String outputFilePath1 = "/fixtures/stylish-output1.txt";
        String outputFilePath2 = "/fixtures/stylish-output2.txt";
        String outputFilePath3 = "/fixtures/stylish-output3.txt";
        String outputFilePath4 = "/fixtures/stylish-output4.txt";
        String outputFilePath5 = "/fixtures/plain-output1.txt";
        String outputFilePath6 = "/fixtures/json-output1.txt";

        absoluteFilePath1 = resourcesPath + filePath1;
        absoluteFilePath2 = resourcesPath + filePath2;
        absoluteFilePath3 = resourcesPath + filePath3;
        absoluteFilePath4 = resourcesPath + filePath4;
        absoluteFilePath5 = resourcesPath + filePath5;
        absoluteFilePath6 = resourcesPath + filePath6;
        absoluteEmptyFilePath = resourcesPath + emptyFilePath1;
        absoluteOutputFilePath1 = resourcesPath + outputFilePath1;
        absoluteOutputFilePath2 = resourcesPath + outputFilePath2;
        absoluteOutputFilePath3 = resourcesPath + outputFilePath3;
        absoluteOutputFilePath4 = resourcesPath + outputFilePath4;
        absoluteOutputFilePath5 = resourcesPath + outputFilePath5;
        absoluteOutputFilePath6 = resourcesPath + outputFilePath6;
    }

    @Test
    void testDiff() throws IOException {
        String expectedOutput = Files.readString(Paths.get(absoluteOutputFilePath1));

        String actualOutput = Differ.generate(absoluteFilePath1, absoluteFilePath2);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testDiffEmptyFiles() throws IOException {
        String expectedOutput = "{\n}\n";

        String actualOutput = Differ.generate(absoluteEmptyFilePath, absoluteEmptyFilePath);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testDiffOneEmptyFile() throws IOException {
        String expectedOutput = Files.readString(Paths.get(absoluteOutputFilePath2));

        String actualOutput = Differ.generate(absoluteFilePath1, absoluteEmptyFilePath);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testDiffFileWithNumericValues() throws IOException {
        String expectedOutput = Files.readString(Paths.get(absoluteOutputFilePath3));

        String actualOutput = Differ.generate(absoluteFilePath3, absoluteFilePath4);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testNestedValues() throws IOException {
        String expectedOutput = Files.readString(Paths.get(absoluteOutputFilePath4));

        String actualOutput = Differ.generate(absoluteFilePath5, absoluteFilePath6);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testPlainFormatter() throws IOException {
        String expectedOutput = Files.readString(Paths.get(absoluteOutputFilePath5));

        String actualOutput = Differ.generate(absoluteFilePath5, absoluteFilePath6, Formatter.PLAIN);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testJsonFormatter() throws IOException {
        String expectedOutput = Files.readString(Paths.get(absoluteOutputFilePath6));

        String actualOutput = Differ.generate(absoluteFilePath5, absoluteFilePath6, Formatter.JSON);

        assertEquals(expectedOutput, actualOutput);
    }
}
