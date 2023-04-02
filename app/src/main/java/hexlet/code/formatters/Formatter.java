package hexlet.code.formatters;

public class Formatter {
    public static final String STYLISH = "stylish";
    public static final String PLAIN = "plain";

    public static FormatterInterface getFormatter(String formatterName) {
        if (formatterName.trim().equals(PLAIN)) {
            return new Plain();
        } else {
            return new Stylish();
        }
    }
}
