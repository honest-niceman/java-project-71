package hexlet.code.formatters;

import java.util.Map;

public interface FormatterInterface {
    StringBuilder proceedAllKeys(Map<String, String> map1,
                                 Map<String, String> map2);
}
