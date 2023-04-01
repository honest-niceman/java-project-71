package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException {
        Map<String, String> map1 = Parser.parse(filePath1);
        Map<String, String> map2 = Parser.parse(filePath2);

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        StringBuilder result = proceedAllKeys(map1, map2, allKeys);

        return result.toString();
    }

    private static StringBuilder proceedAllKeys(Map<String, String> map1,
                                                Map<String, String> map2,
                                                Set<String> allKeys) {
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
        return sb;
    }
}
