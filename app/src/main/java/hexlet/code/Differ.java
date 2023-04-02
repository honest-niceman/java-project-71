package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, new Stylish());
    }

    public static String generate(String filePath1, String filePath2, Formatter formatter) throws IOException {
        Map<String, String> map1 = Parser.parse(filePath1);
        Map<String, String> map2 = Parser.parse(filePath2);

        StringBuilder result = formatter.proceedAllKeys(map1, map2);

        return result.toString();
    }
}
