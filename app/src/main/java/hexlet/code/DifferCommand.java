package hexlet.code;

import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Option;
import static picocli.CommandLine.Parameters;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public class DifferCommand implements Callable<Integer> {

    @Option(names = {"-f", "--format"},
            defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]",
            paramLabel = "format")
    private String format;

    @Parameters(index = "0", description = "path to first file", paramLabel = "filePath1")
    private String filePath1;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filePath2")
    private String filePath2;

    @Override
    public Integer call() {
        try {
            var diff = Differ.generate(filePath1, filePath2, format);
            System.out.println(diff);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }
}
