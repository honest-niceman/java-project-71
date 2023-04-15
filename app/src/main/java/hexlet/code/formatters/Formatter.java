package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static final String STYLISH = "stylish";
    public static final String PLAIN = "plain";
    public static final String JSON = "json";

    public static String format(List<Map<Object, Object>> diff, String formatName) {
        switch (formatName.trim()) {
            case PLAIN -> {
                return new Plain().proceed(diff);
            }
            case JSON -> {
                return new JsonFormatter().proceed(diff);
            }
            case STYLISH -> {
                return new Stylish().proceed(diff);
            }
            default -> throw new IllegalArgumentException(formatName + " is not supported.");
        }
    }
}
