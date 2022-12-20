package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static void main(String[] args) {

    }

    public static Map getWordCount(String sentence) {

        String[] words = sentence.split(" ");
        Map<String, Integer> wordsMap = new HashMap<>();

        if (words.length < 2) {
            return wordsMap;
        }

        for (String word : words) {

            if (wordsMap.containsKey(word)) {
                wordsMap.replace(word, wordsMap.get(word) + 1);
            } else {
                wordsMap.put(word, 1);
            }
        }
        //System.out.println(wordsMap.keySet());
        return wordsMap;
    }

    public static String toString(Map wordCount1) {
        //Map<String, Integer> wordCountMap = wordCount1.keySet();
        String str = "{\n";
        for (var wordCount: ((Map<String, Integer>) wordCount1).entrySet()) {
            str = str + "  " + wordCount.getKey() + ": " +  wordCount.getValue() + "\n";
        }
        str = str + "}";

        if (wordCount1.isEmpty()) {
            return "{}";
        }

        return str;
    }
}

//END
