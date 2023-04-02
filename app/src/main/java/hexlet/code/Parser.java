package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
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
                StringBuilder sb = new StringBuilder("{");
                for (Map.Entry<String, String> nestedEntry : nestedMap.entrySet()) {
                    sb.append(nestedEntry.getKey()).append("=").append(nestedEntry.getValue()).append(", ");
                }
                sb.setLength(sb.length() - 2); // remove the last comma and space
                sb.append("}");
                result.put(key, sb.toString());
            } else {
                String stringValue = (value != null) ? value.toString() : "null";
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
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Object value = jsonObject.get(key);
                if (value instanceof JSONArray) {
                    map.put(key, Arrays.toString(((JSONArray) value).toList().toArray()));
                } else if (value instanceof JSONObject) {
                    Map<String, String> nestedMap = buildMap(value.toString());
                    StringBuilder nestedObject = new StringBuilder("{");
                    for (String nestedKey : nestedMap.keySet()) {
                        nestedObject.append(nestedKey).append("=").append(nestedMap.get(nestedKey)).append(", ");
                    }
                    nestedObject.delete(nestedObject.length() - 2, nestedObject.length());
                    nestedObject.append("}");
                    map.put(key, nestedObject.toString());
                } else {
                    map.put(key, String.valueOf(value));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
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
