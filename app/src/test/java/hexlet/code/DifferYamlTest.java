package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferYamlTest {
    private static String absoluteFilePath1;
    private static String absoluteFilePath2;
    private static String absoluteFilePath3;
    private static String absoluteFilePath4;
    private static String absoluteFilePath5;
    private static String absoluteFilePath6;
    private static String absoluteEmptyFilePath;

    @BeforeAll
    static void setUp() {
        String resourcesPath = new File("src/test/resources").getAbsolutePath();

        String filePath1 = "/yaml/file1.yaml";
        String filePath2 = "/yaml/file2.yaml";
        String filePath3 = "/yaml/file3.yaml";
        String filePath4 = "/yaml/file4.yaml";
        String filePath5 = "/yaml/file5.yaml";
        String filePath6 = "/yaml/file6.yaml";
        String emptyFilePath1 = "/yaml/empty.yaml";

        absoluteFilePath1 = resourcesPath + filePath1;
        absoluteFilePath2 = resourcesPath + filePath2;
        absoluteFilePath3 = resourcesPath + filePath3;
        absoluteFilePath4 = resourcesPath + filePath4;
        absoluteFilePath5 = resourcesPath + filePath5;
        absoluteFilePath6 = resourcesPath + filePath6;
        absoluteEmptyFilePath = resourcesPath + emptyFilePath1;
    }

    @Test
    void testDiff() throws IOException {
        String expectedOutput = """
                                - follow: false
                                  host: hexlet.io
                                - proxy: 123.234.53.22
                                - timeout: 50
                                + timeout: 20
                                + verbose: true
                                """;

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
        String expectedOutput = """
                                - follow: false
                                - host: hexlet.io
                                - proxy: 123.234.53.22
                                - timeout: 50
                                """;

        String actualOutput = Differ.generate(absoluteFilePath1, absoluteEmptyFilePath);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testDiffFileWithNumericValues() throws IOException {
        String expectedOutput = """
                                - a: 1
                                + a: 2
                                - b: 2.5
                                + b: 3.0
                                - c: -10
                                + c: -5
                                """;

        String actualOutput = Differ.generate(absoluteFilePath3, absoluteFilePath4);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testNestedValues() throws IOException {
        String expectedOutput = """
                                  chars1: [a, b, c]
                                - chars2: [d, e, f]
                                + chars2: false
                                - checked: false
                                + checked: true
                                - default: null
                                + default: [value1, value2]
                                - id: 45
                                + id: null
                                - key1: value1
                                + key2: value2
                                  numbers1: [1, 2, 3, 4]
                                - numbers2: [2, 3, 4, 5]
                                + numbers2: [22, 33, 44, 55]
                                - numbers3: [3, 4, 5]
                                + numbers4: [4, 5, 6]
                                + obj1: {nestedKey=value, isNested=true}
                                - setting1: Some value
                                + setting1: Another value
                                - setting2: 200
                                + setting2: 300
                                - setting3: true
                                + setting3: none
                                """;

        String actualOutput = Differ.generate(absoluteFilePath5, absoluteFilePath6);

        assertEquals(expectedOutput, actualOutput);
    }
}
