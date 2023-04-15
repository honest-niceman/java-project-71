package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.parsers.Parser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        Map<Object, Object> data1 = getData(filePath1);
        Map<Object, Object> data2 = getData(filePath2);

        List<Map<Object, Object>> diff = Tree.build(data1, data2);

        return Formatter.format(diff, formatName);
    }


    private static Map<Object, Object> getData(String filePath) throws Exception {
        Path fullPath = Paths.get(filePath).toAbsolutePath().normalize();

        if (!Files.exists(fullPath)) {
            throw new Exception("File '" + fullPath + "' does not exist");
        }

        String content = Files.readString(fullPath);
        String dataFormat = getDataFormat(filePath);

        return Parser.parse(content, dataFormat);
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.trim().lastIndexOf('.');
        return index > 0 ? filePath.substring(index + 1) : "";
    }
}
