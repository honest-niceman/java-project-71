package hexlet.code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException {
        Map<String, String> map1 = readJsonFile(filePath1);
        Map<String, String> map2 = readJsonFile(filePath2);

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        StringBuilder sb = new StringBuilder();
        for (String key : allKeys) {
            String value1 = map1.get(key);
            String value2 = map2.get(key);
            if (value1 != null && value2 != null) {
                if (!value1.equals(value2)) {
                    sb.append("- ").append(key).append(": ").append(value1).append("\n");
                    sb.append("+ ").append(key).append(": ").append(value2).append("\n");
                } else {
                    sb.append("  ").append(key).append(": ").append(value1).append("\n");
                }
            } else if (value1 != null) {
                sb.append("- ").append(key).append(": ").append(value1).append("\n");
            } else if (value2 != null) {
                sb.append("+ ").append(key).append(": ").append(value2).append("\n");
            }
        }

        return sb.toString();
    }

    private static Map<String, String> readJsonFile(String filePath) throws IOException {
        Map<String, String> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line.trim());
            }
            String json = sb.toString();
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
        }
        return map;
    }

    private static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
