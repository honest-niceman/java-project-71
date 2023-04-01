package hexlet.code;

import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public class DifferCommand implements Callable<Integer> {

    @Override
    public Integer call() {
        return null;
    }
}
