package hexlet.code.formatters;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Plain implements FormatterInterface {
    public StringBuilder proceedAllKeys(Map<String, String> map1, Map<String, String> map2) {
        StringBuilder sb = new StringBuilder();
        Set<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());
        for (String key : allKeys) {
            String beforeValue = map1.get(key);
            String afterValue = map2.get(key);
            if (beforeValue == null && afterValue == null) {
                continue;
            }
            if (beforeValue == null) {
                sb.append(String.format("Property '%s' was added with value: %s\n", key, formatValue(afterValue)));
                continue;
            }
            if (afterValue == null) {
                sb.append(String.format("Property '%s' was removed\n", key));
                continue;
            }
            if (!beforeValue.equals(afterValue)) {
                sb.append(String.format("Property '%s' was updated. From %s to %s\n",
                                        key,
                                        formatValue(beforeValue),
                                        formatValue(afterValue)));
            }
        }
        return sb;
    }

    private String formatValue(String value) {
        if (value == null || value.equals("null")) {
            return "null";
        }
        if (value.equals("true") || value.equals("false")) {
            return value;
        }
        if (value.matches("-?\\d+")) {
            return value;
        }
        if (value.matches("\\[.*]") || value.contains("{")) {
            return "[complex value]";
        }
        return String.format("'%s'", value);
    }
}

