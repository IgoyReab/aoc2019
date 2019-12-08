package aoc.helper;

import com.google.common.base.CharMatcher;

import java.text.DecimalFormat;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

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

    public static List<Integer>  convertStringToIntegerList(String str) {
        return new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                return Integer.parseInt( String.valueOf(str.charAt(index)));
            }

            @Override
            public int size() {
                return str.length();
            }
        };
    }

    public static List<Character>  convertStringToCharList(String str) {
        return new AbstractList<Character>() {
            @Override
            public Character get(int index) {
                return str.charAt(index);
            }

            @Override
            public int size() {
                return str.length();
            }
        };
    }


}
