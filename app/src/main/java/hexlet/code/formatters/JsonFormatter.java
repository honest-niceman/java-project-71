package hexlet.code.formatters;

import hexlet.code.formatters.utils.JsonUtils;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class JsonFormatter implements FormatterInterface {

    @Override
    public String proceedAllKeys(Map<String, String> map1, Map<String, String> map2) {
        Set<String> allKeys = getAllKeys(map1, map2);
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (String key : allKeys) {
            String value1 = map1.get(key);
            String value2 = map2.get(key);
            if (value1 != null && value2 != null) {
                appendUpdate(sb, key, value1, value2);
            } else if (value1 != null) {
                appendRemove(sb, key, value1);
            } else if (value2 != null) {
                appendAdd(sb, key, value2);
            }
        }
        removeLastComma(sb);
        sb.append("}");
        return sb.toString();
    }

    private static void removeLastComma(StringBuilder sb) {
        if (sb.charAt(sb.length() - 2) == ',') {
            sb.deleteCharAt(sb.length() - 2);
        }
    }

    private static Set<String> getAllKeys(Map<String, String> map1, Map<String, String> map2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        return allKeys;
    }

    private static void appendAdd(StringBuilder sb, String key, String value2) {
        sb.append("  \"").append(key).append("\": {\n");
        sb.append("    \"+\": ").append(JsonUtils.escapeString(value2)).append("\n");
        sb.append("  },\n");
    }

    private static void appendRemove(StringBuilder sb, String key, String value1) {
        sb.append("  \"").append(key).append("\": {\n");
        sb.append("    \"-\": ").append(JsonUtils.escapeString(value1)).append("\n");
        sb.append("  },\n");
    }

    private static void appendUpdate(StringBuilder sb, String key, String value1, String value2) {
        if (!value1.equals(value2)) {
            sb.append("  \"").append(key).append("\": {\n");
            sb.append("    \"+\": ").append(JsonUtils.escapeString(value2)).append(",\n");
            sb.append("    \"-\": ").append(JsonUtils.escapeString(value1)).append("\n");
            sb.append("  },\n");
        } else {
            sb.append("  \"").append(key).append("\": ").append(JsonUtils.escapeString(value1)).append(",\n");
        }
    }
}
