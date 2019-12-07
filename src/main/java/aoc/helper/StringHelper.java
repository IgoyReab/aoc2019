package aoc.helper;

import com.google.common.base.CharMatcher;

import java.text.DecimalFormat;
import java.util.Arrays;

public class StringHelper {
    public static int countChars(String testString, char testChar) {
        return CharMatcher.is(testChar).countIn(testString);
    }

    public static String intToString5(int num) {
        int digits = 5;
        char[] zeros = new char[digits];
        Arrays.fill(zeros, '0');
        DecimalFormat df = new DecimalFormat(String.valueOf(zeros));
        return df.format(num);
    }


}
