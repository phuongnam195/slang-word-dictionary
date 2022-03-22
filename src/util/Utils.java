package util;

import java.util.ArrayList;

public class Utils {
    public static ArrayList<Character> stringToCharList(String s) {
        ArrayList<Character> chars = new ArrayList<>();
        for (Character ch : s.toCharArray()) {
            chars.add(ch);
        }
        return chars;
    }
}
