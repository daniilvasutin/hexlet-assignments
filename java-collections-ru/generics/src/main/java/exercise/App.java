package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// BEGIN
public class App {
    public static void main(String[] args) {

    }

    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> dictionary) {

        List<Map<String, String>> foundBooks = new ArrayList<>();

        for (Map<String, String> book : books) {
            boolean check = false;
            for (Map.Entry<String, String> where : dictionary.entrySet()) {
                if (book.containsValue(where.getValue())) {
                    if (book.containsKey(where.getKey())) {
                        check = true;
                    } else {
                        check = false;
                        break;
                    }
                } else {
                    check = false;
                    break;
                }
            }
            if (check) {
                foundBooks.add(book);
            }
        }

        return foundBooks;
    }
}

//END

