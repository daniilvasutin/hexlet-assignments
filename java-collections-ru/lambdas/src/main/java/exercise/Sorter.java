package exercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> mans) {
        return mans.stream()
                .filter(man -> man.get("gender").equals("male") && Objects.nonNull(man))
                .sorted((date1, date2) -> compareTo(date1.get("birthday"), date2.get("birthday")))
                .map(man -> man.get("name"))
                .collect(Collectors.toList());

    }

    public static int compareTo (String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date Date1 = null;
        try {
            Date1 = format.parse(date1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date Date2 = null;
        try {
            Date2 = format.parse(date2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return Date1.compareTo(Date2);
        }
    }
// END
