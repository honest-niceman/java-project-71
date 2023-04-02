package hexlet.code;

import picocli.CommandLine;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        String filePath1 = "app/src/main/resources/file1.json";
        String filePath2 = "app/src/main/resources/file2.json";
        int exitCode = new CommandLine(new DifferCommand()).execute(filePath1, filePath2);
        System.exit(exitCode);
    }
}
