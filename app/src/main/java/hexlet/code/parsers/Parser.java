package hexlet.code.parsers;

import hexlet.code.utils.FileType;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, String> parse(String filePath) throws IOException {
        FileType type = getFilesType(filePath);
        if (type.equals(FileType.JSON)) {
            return JsonParser.getMap(filePath);
        } else {
            return YamlParser.getMap(filePath);
        }
    }

    private static FileType getFilesType(String filePath) {
        if (filePath.endsWith(".yaml") || filePath.endsWith(".yml")) {
            return FileType.YAML;
        } else if (filePath.endsWith(".json")) {
            return FileType.JSON;
        } else {
            throw new IllegalArgumentException("Supported extensions: .json, .yaml, .yml");
        }
    }

}
