package aoc.helper;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static aoc.helper.ListHelper.convertStringToCharList;
import static org.junit.Assert.assertEquals;

public class ListHelperTest{

    @Test
    public void convertStringToCharListTest(){
        String inputString = "TestStringEen";
        List<Character> result = convertStringToCharList(inputString);
        assertEquals(result.toString(), "[T, e, s, t, S, t, r, i, n, g, E, e, n]");
    }
}
