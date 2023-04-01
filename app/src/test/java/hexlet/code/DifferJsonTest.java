package hexlet.code;

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
    private static String absoluteEmptyFilePath;

    @BeforeAll
    static void setUp() {
        String resourcesPath = new File("src/test/resources").getAbsolutePath();

        String filePath1 = "/json/file1.json";
        String filePath2 = "/json/file2.json";
        String filePath3 = "/json/file3.json";
        String filePath4 = "/json/file4.json";
        String emptyFilePath1 = "/json/empty.json";

        absoluteFilePath1 = resourcesPath + filePath1;
        absoluteFilePath2 = resourcesPath + filePath2;
        absoluteFilePath3 = resourcesPath + filePath3;
        absoluteFilePath4 = resourcesPath + filePath4;
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
}
