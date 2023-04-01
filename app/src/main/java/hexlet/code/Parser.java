package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, String> parse(String filePath) throws IOException {
        FileType type = getFilesType(filePath);
        return readFileToMap(filePath, type);
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

    private static Map<String, String> readFileToMap(String filePath, FileType type) throws IOException {
        if (type.equals(FileType.JSON)) {
            return readJsonFile(filePath);
        } else {
            return readYamlFile(filePath);
        }
    }

    public static Map<String, String> readYamlFile(String filePath) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            Map<String, Object> map = mapper.readValue(new File(filePath), Map.class);
            return flatten(map);
        } catch (MismatchedInputException e) {
            return new HashMap<>();
        }
    }

    private static Map<String, String> flatten(Map<String, Object> map) {
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                Map<String, String> nestedMap = flatten((Map<String, Object>) value);
                for (Map.Entry<String, String> nestedEntry : nestedMap.entrySet()) {
                    String nestedKey = key + "." + nestedEntry.getKey();
                    String nestedValue = nestedEntry.getValue();
                    result.put(nestedKey, nestedValue);
                }
            } else {
                String stringValue = (value != null) ? value.toString() : "";
                result.put(key, stringValue);
            }
        }
        return result;
    }

    private static Map<String, String> readJsonFile(String filePath) throws IOException {
        String json = getFileAsString(filePath);
        if (json.isEmpty()) {
            return new HashMap<>();
        }
        return buildMap(json);
    }

    private static Map<String, String> buildMap(String json) {
        Map<String, String> map = new HashMap<>();
        json = json.substring(1, json.length() - 1); // remove braces
        String[] entries = json.split(",");
        for (String entry : entries) {
            String[] parts = entry.split(":");
            String key = parts[0].trim().substring(1, parts[0].length() - 1); // remove quotes
            String value = parts[1].trim();
            if (value.equals("true") || value.equals("false") || isNumeric(value)) {
                map.put(key, value);
            } else {
                map.put(key, value.substring(1, value.length() - 1)); // remove quotes
            }
        }
        return map;
    }

    private static String getFileAsString(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line.trim());
            }
            return sb.toString();
        }
    }

    private static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
