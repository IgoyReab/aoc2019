package aoc.day23;

import aoc.day25.Day25;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day23Test {

    @Test
    public void testPart1(){
        List<String> input;
        String result;

        input = Arrays.asList("1","2");

        result = new Day23().part1(input);
        assertEquals("", result);
    }

    @Test
    public void testPart2(){
        List<String> input;
        String result;

        input = Arrays.asList("1","2");

        result = new Day23().part2(input);
        assertEquals("", result);

    }
}
