package exercise;

import java.util.List;

// BEGIN
class App {
    public static int getCountOfFreeEmails(List<String> emails) {

        long amount = emails.stream()
                .filter((name) -> name.endsWith("@gmail.com")
                        || name.endsWith("@yandex.ru")
                        || name.endsWith("@hotmail.com"))
                .count();

        return Math.toIntExact(amount);
    }
}
// END
