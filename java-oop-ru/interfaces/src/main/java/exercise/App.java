package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static List<String> buildApartmentsList(List<Home> apartments, int topUp) {

        var temp = apartments.stream().sorted(Home::compareTo).toList();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < topUp; i++) {
            result.add(temp.get(i).toString());
        }
        return result;
    }

}
// END
