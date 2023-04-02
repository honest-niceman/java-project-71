package hexlet.code.formatters;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Stylish implements Formatter {
    public StringBuilder proceedAllKeys(Map<String, String> map1,
                                        Map<String, String> map2) {
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
        return sb;
    }
}
