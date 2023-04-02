package hexlet.code;

import hexlet.code.formatters.Formatter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferJsonTest {

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

        String filePath1 = "/json/file1.json";
        String filePath2 = "/json/file2.json";
        String filePath3 = "/json/file3.json";
        String filePath4 = "/json/file4.json";
        String filePath5 = "/json/file5.json";
        String filePath6 = "/json/file6.json";
        String emptyFilePath1 = "/json/empty.json";

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

    @Test
    void testPlainFormatter() throws IOException {
        String expectedOutput = """
                                Property 'chars2' was updated. From [complex value] to false
                                Property 'checked' was updated. From false to true
                                Property 'default' was updated. From null to [complex value]
                                Property 'id' was updated. From 45 to null
                                Property 'key1' was removed
                                Property 'key2' was added with value: 'value2'
                                Property 'numbers2' was updated. From [complex value] to [complex value]
                                Property 'numbers3' was removed
                                Property 'numbers4' was added with value: [complex value]
                                Property 'obj1' was added with value: [complex value]
                                Property 'setting1' was updated. From 'Some value' to 'Another value'
                                Property 'setting2' was updated. From 200 to 300
                                Property 'setting3' was updated. From true to 'none'
                                """;

        String actualOutput = Differ.generate(absoluteFilePath5, absoluteFilePath6, Formatter.PLAIN);

        assertEquals(expectedOutput, actualOutput);
    }
}
