package aoc.helper;

import java.util.AbstractList;
import java.util.List;
import java.util.stream.Collectors;

public class ListHelper {

    public static List<Character> convertStringToCharList(String str)
    {
        return new AbstractList<Character>() {

            @Override
            public Character get(int index)
            {
                return str.charAt(index);
            }

            @Override
            public int size()
            {
                return str.length();
            }
        };
    }
}
