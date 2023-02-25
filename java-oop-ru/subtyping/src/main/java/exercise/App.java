package exercise;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage inMemKV) {

        KeyValueStorage oldInMemKV = new InMemoryKV(inMemKV.toMap());

        for (var item : oldInMemKV.toMap().entrySet()) {
            var oldKey = item.getKey();
            var oldValue = item.getValue();

            inMemKV.set(oldValue, oldKey);
            inMemKV.unset(oldKey);
        }
        ////work up

//        Iterator<Map.Entry<String, String>> it = inMemKV.toMap().entrySet().iterator();
//        while (it.hasNext()) {
//
//            Map.Entry<String, String> entry = it.next();
//            entry.
//        }

    }
}
// END
