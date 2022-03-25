package util;

import java.util.ArrayList;

public class Utils {
    public static ArrayList<Character> stringToCharList(String s) {
        ArrayList<Character> chars = new ArrayList<>();
        char[] charArray = s.toCharArray();
        for (Character ch : charArray) {
            chars.add(ch);
        }
        return chars;
    }

    public static ArrayList<String> stringToWordList(String s) {
        ArrayList<String> words = new ArrayList<>();
        String[] wordArray = s.split(" ");
        for (String w : wordArray) {
            words.add(w);
        }
        return words;
    }
}
