package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        Field[] fields = address.getClass().getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (Field field : fields) {
            var fieldAnotation = field.getAnnotation(NotNull.class);
            field.setAccessible(true);
            try {
                var fieldValue = field.get(address);
                if (fieldAnotation != null && fieldValue == null) {
                    list.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
}
// END
