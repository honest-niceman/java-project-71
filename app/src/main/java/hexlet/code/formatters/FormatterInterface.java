package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public interface FormatterInterface {
    String proceed(List<Map<Object, Object>> diff);
}
