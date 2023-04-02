package hexlet.code;

import hexlet.code.formatters.Formatter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferJsonTest {

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

        String filePath1 = "/inputs/json/file1.json";
        String filePath2 = "/inputs/json/file2.json";
        String filePath3 = "/inputs/json/file3.json";
        String filePath4 = "/inputs/json/file4.json";
        String filePath5 = "/inputs/json/file5.json";
        String filePath6 = "/inputs/json/file6.json";
        String emptyFilePath1 = "/inputs/json/empty.json";
        String outputFilePath1 = "/outputs/stylish/output1.txt";
        String outputFilePath2 = "/outputs/stylish/output2.txt";
        String outputFilePath3 = "/outputs/stylish/output3.txt";
        String outputFilePath4 = "/outputs/stylish/output4.txt";
        String outputFilePath5 = "/outputs/plain/output1.txt";
        String outputFilePath6 = "/outputs/json/output1.txt";

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
        String expectedOutput = "";

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
