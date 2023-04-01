package hexlet.code;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        String filePath1 = "app/src/main/resources/file1.json";
        String filePath2 = "/Users/vlasov/IdeaProjects/java-project-71/app/src/main/resources/file2.json";
        int exitCode = new CommandLine(new DifferCommand()).execute("-h");
        System.exit(exitCode);
    }
}
