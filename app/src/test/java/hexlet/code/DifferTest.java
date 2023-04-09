package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DifferTest {
    private static String jsonOutput;
    private static String plainOutput;
    private static String stylishOutput;

    @BeforeAll
    public static void beforeAll() throws Exception {
        jsonOutput = readFixture("json_output.json");
        plainOutput = readFixture("plain_output.txt");
        stylishOutput = readFixture("stylish_output.txt");
    }

    private static String getFixtureAbsolutePath(String fileName) {
        String resourcesPath = new File("src/test/resources").getAbsolutePath();
        return resourcesPath + "/fixtures/" + fileName;
    }

    private static String readFixture(String fileName) throws Exception {
        return Files.readString(Paths.get(getFixtureAbsolutePath(fileName))).trim();
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yaml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixtureAbsolutePath("file1" + format);
        String filePath2 = getFixtureAbsolutePath("file2" + format);

        Assertions.assertEquals(stylishOutput, Differ.generate(filePath1, filePath2));
        Assertions.assertEquals(stylishOutput, Differ.generate(filePath1, filePath2, "stylish"));
        Assertions.assertEquals(plainOutput, Differ.generate(filePath1, filePath2, "plain"));
        Assertions.assertEquals(jsonOutput, Differ.generate(filePath1, filePath2, "json"));
    }
}