package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Tree {
    public static List<Map<Object, Object>> build(Map<Object, Object> data1, Map<Object, Object> data2) {
        Set<Object> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        Set<Object> sortedKeys = new TreeSet<>(keys);

        return sortedKeys.stream()
                         .map(key -> getDifferenceMap(data1, data2, key))
                         .collect(Collectors.toList());
    }

    private static Map<Object, Object> getDifferenceMap(Map<Object, Object> data1,
                                                        Map<Object, Object> data2,
                                                        Object key) {
        Object value1 = data1.get(key);
        Object value2 = data2.get(key);

        Map<Object, Object> node = new HashMap<>();
        node.put("key", key);

        if (!data2.containsKey(key)) {
            node.put("type", "deleted");
            node.put("value", value1);
        } else if (!data1.containsKey(key)) {
            node.put("type", "added");
            node.put("value", value2);
        } else if (!isEqual(value1, value2)) {
            node.put("type", "changed");
            node.put("value1", value1);
            node.put("value2", value2);
        } else {
            node.put("type", "unchanged");
            node.put("value", value1);
        }

        return node;
    }

    private static Boolean isEqual(Object value1, Object value2) {
        return (value1 == null || value2 == null) ? value1 == value2 : value1.equals(value2);
    }
}

