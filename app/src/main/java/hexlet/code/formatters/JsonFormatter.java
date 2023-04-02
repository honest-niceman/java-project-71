package hexlet.code.formatters;

import hexlet.code.formatters.utils.JsonUtils;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class JsonFormatter implements FormatterInterface {

    @Override
    public StringBuilder proceedAllKeys(Map<String, String> map1, Map<String, String> map2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (String key : allKeys) {
            String value1 = map1.get(key);
            String value2 = map2.get(key);
            if (value1 != null && value2 != null) {
                if (!value1.equals(value2)) {
                    sb.append("  \"").append(key).append("\": {\n");
                    sb.append("    \"+\": ").append(JsonUtils.escapeString(value2)).append(",\n");
                    sb.append("    \"-\": ").append(JsonUtils.escapeString(value1)).append("\n");
                    sb.append("  },\n");
                } else {
                    sb.append("  \"").append(key).append("\": ").append(JsonUtils.escapeString(value1)).append(",\n");
                }
            } else if (value1 != null) {
                sb.append("  \"").append(key).append("\": {\n");
                sb.append("    \"-\": ").append(JsonUtils.escapeString(value1)).append("\n");
                sb.append("  },\n");
            } else if (value2 != null) {
                sb.append("  \"").append(key).append("\": {\n");
                sb.append("    \"+\": ").append(JsonUtils.escapeString(value2)).append("\n");
                sb.append("  },\n");
            }
        }
        if (sb.charAt(sb.length() - 2) == ',') {
            sb.deleteCharAt(sb.length() - 2);
        }
        sb.append("}\n");
        return sb;
    }
}