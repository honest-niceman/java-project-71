package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Plain implements FormatterInterface {
    public String proceed(List<Map<Object, Object>> diff) {
        StringBuilder output = new StringBuilder();
        for (Map<Object, Object> map : diff) {
            String nodeString = Plain.stringifyNode(map);
            if (!nodeString.isEmpty()) {
                output.append(nodeString);
                output.append("\n");
            }
        }
        return output.toString().trim();
    }

    private static String stringifyNode(Map<Object, Object> node) {
        String type = (String) node.get("type");
        String key = (String) node.get("key");
        String value = stringify(node.get("value"));
        String value1 = stringify(node.get("value1"));
        String value2 = stringify(node.get("value2"));

        return switch (type) {
            case "added" -> "Property '" + key + "' was added" + " with value: " + value;
            case "deleted" -> "Property '" + key + "' was removed";
            case "changed" -> "Property '" + key + "' was updated." + " From " + value1 + " to " + value2;
            case "unchanged" -> "";
            default -> throw new RuntimeException("Unknown node type: '" + type + "'");
        };
    }

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        return value.toString();
    }

}

