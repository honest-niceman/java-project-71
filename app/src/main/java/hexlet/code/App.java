package hexlet.code;

import hexlet.code.commands.DifferCommand;
import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new DifferCommand()).execute(args);
        System.exit(exitCode);
    }
}
