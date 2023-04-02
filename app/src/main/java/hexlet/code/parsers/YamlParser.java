package hexlet.code.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class YamlParser {

    public static Map<String, String> getMap(String filePath) throws IOException {
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
                proceedNestedMap(result, key, nestedMap);
            } else {
                String stringValue = (value != null) ? value.toString() : "null";
                result.put(key, stringValue);
            }
        }
        return result;
    }

    private static void proceedNestedMap(Map<String, String> result, String key, Map<String, String> nestedMap) {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<String, String> nestedEntry : nestedMap.entrySet()) {
            sb.append(nestedEntry.getKey()).append("=").append(nestedEntry.getValue()).append(", ");
        }
        sb.setLength(sb.length() - 2); // remove the last comma and space
        sb.append("}");
        result.put(key, sb.toString());
    }
}
