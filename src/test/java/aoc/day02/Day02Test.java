package aoc.day02;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day02Test {

    @Test
    public void testPart1(){
        List<String> input;
        String result;

        input = Arrays.asList("abcdef","bababc","abbcde","abcccd","aabcdd","abcdee","ababab");
        result = new Day02().part1(input);
        assertEquals("12", result);
    }

    @Test
    public void testPart2(){
        List<String> input;
        String result;

        input = Arrays.asList("abcde","fghij","klmno","pqrst","fguij","axcye","wvxyz");
        result = new Day02().part2(input);
        System.out.println("Result = " + result);
        assertEquals("fgij", result);
    }
}
