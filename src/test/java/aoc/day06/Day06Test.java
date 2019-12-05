package aoc.day06;

import aoc.day99.Day99;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day06Test {

    @Test
    public void testPart1(){
        List<String> input;
        String result;

        input = Arrays.asList("1","2");

        result = new Day99().part1(input);
        assertEquals("", result);
    }

    @Test
    public void testPart2(){
        List<String> input;
        String result;

        input = Arrays.asList("1","2");

        result = new Day99().part2(input);
        assertEquals("", result);

    }
}
