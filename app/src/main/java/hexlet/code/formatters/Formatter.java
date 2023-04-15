package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<Object, Object>> diff, String formatName) {
        switch (formatName.trim()) {
            case "plain" -> {
                return new Plain().proceed(diff);
            }
            case "json" -> {
                return new Json().proceed(diff);
            }
            case "stylish" -> {
                return new Stylish().proceed(diff);
            }
            default -> throw new IllegalArgumentException(formatName + " is not supported.");
        }
    }
}
