package exercise;

import java.util.ArrayList;
import java.util.Arrays;

// BEGIN
class App {
    public static void main(String[] args) {
        //System.out.println("Hello, Hexlet!");
    }

    public static boolean scrabble(String symbols, String word) {

        int length = word.length();
        String[] letters = symbols.split("");
        ArrayList coll = new ArrayList(Arrays.asList(letters));

        for (int i = 0; i < length; i++) {
            String current = word.substring(i, i + 1).toLowerCase();

            if (!coll.contains(current)) {
                return false;
            }

            coll.remove(current);
        }

        return true;
    }
}
//END
