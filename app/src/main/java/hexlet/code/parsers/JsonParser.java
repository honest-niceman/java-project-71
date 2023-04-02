package hexlet.code.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonParser {
    public static Map<String, String> getMap(String filePath) throws IOException {
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
                    proceedNestedObject(map, key, nestedMap);
                } else {
                    map.put(key, String.valueOf(value));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static void proceedNestedObject(Map<String, String> map, String key, Map<String, String> nestedMap) {
        StringBuilder nestedObject = new StringBuilder("{");
        for (String nestedKey : nestedMap.keySet()) {
            nestedObject.append(nestedKey).append("=").append(nestedMap.get(nestedKey)).append(", ");
        }
        nestedObject.delete(nestedObject.length() - 2, nestedObject.length());
        nestedObject.append("}");
        map.put(key, nestedObject.toString());
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
}
