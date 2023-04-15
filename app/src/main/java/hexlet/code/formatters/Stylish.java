package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Stylish implements FormatterInterface {
    private static final int SPACES_COUNT = 4;

    private static String buildIndent() {
        return " ".repeat(SPACES_COUNT - 2);
    }

    public String proceed(List<Map<Object, Object>> diff) {
        StringBuilder output = new StringBuilder();
        output.append("{\n");
        for (Map<Object, Object> map : diff) {
            output.append(Stylish.getNode(map));
            output.append("\n");
        }
        output.append("}");
        return output.toString();
    }

    private static String getNode(Map<Object, Object> node) {
        String indent = buildIndent();
        String type = (String) node.get("type");
        String key = (String) node.get("key");
        String value = String.valueOf(node.get("value"));
        String value1 = String.valueOf(node.get("value1"));
        String value2 = String.valueOf(node.get("value2"));

        return switch (type) {
            case "added" -> indent + "+ " + key + ": " + value;
            case "deleted" -> indent + "- " + key + ": " + value;
            case "changed" -> indent + "- " + key + ": " + value1 + "\n" + indent + "+ " + key + ": " + value2;
            case "unchanged" -> indent + "  " + key + ": " + value;
            default -> throw new RuntimeException("Unknown node type: '" + type + "'");
        };
    }

}
