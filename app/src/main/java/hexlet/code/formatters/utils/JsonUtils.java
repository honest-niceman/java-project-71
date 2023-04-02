package hexlet.code.formatters.utils;

public class JsonUtils {
    private static final String ESCAPE_BACKSLASH = "\\\\";
    private static final String ESCAPE_DOUBLE_QUOTE = "\\\"";
    private static final String ESCAPE_NEWLINE = "\\n";
    private static final String ESCAPE_CARRIAGE_RETURN = "\\r";
    private static final String ESCAPE_TAB = "\\t";
    private static final String ESCAPE_BACKSPACE = "\\b";
    private static final String ESCAPE_FORM_FEED = "\\f";

    public static String escapeString(String str) {
        return "\"" + str.replace("\\", ESCAPE_BACKSLASH)
                         .replace("\"", ESCAPE_DOUBLE_QUOTE)
                         .replace("\n", ESCAPE_NEWLINE)
                         .replace("\r", ESCAPE_CARRIAGE_RETURN)
                         .replace("\t", ESCAPE_TAB)
                         .replace("\b", ESCAPE_BACKSPACE)
                         .replace("\f", ESCAPE_FORM_FEED) + "\"";
    }
}
