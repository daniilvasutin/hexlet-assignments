package exercise;

import java.util.*;
import java.util.stream.Collectors;

// BEGIN
class App {
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static LinkedHashMap genDiff(Map<String, Object> data1, Map<String, Object> data2) {

        LinkedHashMap<String, Object> tempMap = new LinkedHashMap<>();

        for (String key : data2.keySet()) {
            if (data1.containsKey(key) && data1.get(key).equals(data2.get(key))) {
                tempMap.put(key, "unchanged");
            } else if (data1.containsKey(key) && !data1.get(key).equals(data2.get(key))) {
                tempMap.put(key, "changed");
            } else if (!data1.containsKey(key)) {
                tempMap.put(key, "added");
            }
        }
        for (String key : data1.keySet()) {
            if(!data2.containsKey(key)) {
                tempMap.put(key, "deleted");
            }
        }

        LinkedHashMap<String, Object> sortedTempMap =
            tempMap.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> x,
                    LinkedHashMap::new));

        System.out.println("After for");
        System.out.println(sortedTempMap.toString());

        return sortedTempMap;
    }
}
//END
