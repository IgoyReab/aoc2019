package aoc.helper;

import com.google.common.base.CharMatcher;

public class StringHelper {
    public static int countChars(String testString, char testChar) {
        return CharMatcher.is(testChar).countIn(testString);
    }


}
